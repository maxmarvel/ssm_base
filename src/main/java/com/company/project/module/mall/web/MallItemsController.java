package com.company.project.module.mall.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.mall.model.MallItems;
import com.company.project.module.mall.service.MallItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by company.chen on 2019/10/21.
 */
@RestController
@RequestMapping("/mall/items")
@Api(description = "商品管理")
public class MallItemsController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MallItemsService mallItemsService;
	@GetMapping()
	@ApiOperation(value = "请求地址", notes = "商品列表地址")
	@RequiresPermissions("item:query")
	public ModelAndView list() {
		return new ModelAndView("/module/mall/items/list");
	}

	@PostMapping("/list")
	@ApiOperation(value = "列表", notes = "商品列表")
	@RequiresPermissions("item:query")
	public Result list(QueryRequest queryres) {
		try {
			Map<String, Object> result = super.selectByPageNumSize(queryres, () -> this.mallItemsService.findAll());
			return ResultGenerator.genSuccessResult(result);
		} catch (Exception e) {
			logger.error("获取商品列表失败", e);
			return ResultGenerator.genFailResult("获取商品列表失败！");
		}
	}

	@Log("商品详细数据")
	@PostMapping("/detail")
	@ApiOperation(value = "明细", notes = "商品详细数据")
	public Result detail(@RequestParam String id) {
		MallItems mallItems = mallItemsService.findById(id);
		return ResultGenerator.genSuccessResult(mallItems);
	}
	@Log("商品新增")
	@PostMapping("/add")
	@RequiresPermissions("item:create")
	@ApiOperation(value = "新增", notes = "商品新增")
	public Result add(MallItems mallItems) {
		try {

			mallItemsService.save(mallItems);
			return ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			logger.error("新增商品信息失败", e);
			return ResultGenerator.genFailResult("新增商品信息失败，请联系网站管理员！");
		}
	}
	@Log("商品删除")
	@PostMapping("/delete")
	@ApiOperation(value = "删除", notes = "商品删除")
	public Result delete(@RequestParam String id) {
		try {
			mallItemsService.deleteById(id);
			return ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			logger.error("删除商品信息失败", e);
			return ResultGenerator.genFailResult("删除商品信息失败，请联系网站管理员！");
		}
	}
	@Log("商品修改")
	@PostMapping("/update")
	@RequiresPermissions("item:update")
	@ApiOperation(value = "修改", notes = "商品修改")
	public Result update(MallItems mallItems) {
		try {
			mallItemsService.update(mallItems);
			return ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			logger.error("修改商品信息失败", e);
			return ResultGenerator.genFailResult("修改商品信息失败，请联系网站管理员！");
		}

	}
}
