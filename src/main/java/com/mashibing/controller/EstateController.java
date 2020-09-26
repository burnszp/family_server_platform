package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.*;
import com.mashibing.returnJson.ReturnObject;
import com.mashibing.service.EstateService;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {}, allowedHeaders = "*", allowCredentials = "true")
public class EstateController {
    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public String selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.getCompany();
        return JSONObject.toJSONString(new ReturnObject(companies));
    }

    @RequestMapping("/estate/insertEstate")
    public String insertEstate(FcEstate fcEstate){
        System.out.println("insertEstate");
        Integer result = estateService.insertEstate(fcEstate);
        if (result == 0){
            return JSONObject.toJSONString(new ReturnObject("0", "插入失败"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("1", "插入成功"));
        }
    }

    @RequestMapping("/estate/selectBuilding")
    public String selectBuilding(Integer buildingNumber, String estateCode){
        System.out.println("selectBuilding");
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        System.out.println("fcBuildings");
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));
    }

    @RequestMapping("/estate/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding){
        System.out.println("updateBuilding");
        Integer res = estateService.updateBuilding(fcBuilding);
        System.out.println(res);
        if (res > 0){
            return JSONObject.toJSONString(new ReturnObject("成功"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("失败"));
        }
    }

    @RequestMapping("/estate/selectUnit")
    public String selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("selectUnit");
        List<FcUnit> allUnit = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            allUnit.addAll(estateService.selectUnit(unitMessage));
        }
        return JSONObject.toJSONString(new ReturnObject(allUnit));
    }

    @RequestMapping("/estate/updateUnit")
    public String updateUnit(FcUnit fcUnit) {
        System.out.println("updateUnit");
        Integer res = estateService.updateUnit(fcUnit);
        System.out.println(res);
        if (res > 0) {
            return JSONObject.toJSONString(new ReturnObject("成功"));
        } else {
            return JSONObject.toJSONString(new ReturnObject("失败"));
        }
    }

    @RequestMapping("/estate/insertCell")
    public String insertCell(@RequestBody CellMessage[] cellMessages){
        System.out.println("insertCell");
        List<FcCell> fcCells = estateService.insertCell(cellMessages);
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }

    @RequestMapping("/estate/selectBuildingByEstate")
    public String selectBuildingByEstate(String estateCode){
        System.out.println("estate:" + estateCode);
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstate(estateCode);
        System.out.println("---------------------");
        System.out.println(fcBuildings);
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public String selectUnitByBuildingCode(String buildingCode){
        System.out.println("buildingCode:" + buildingCode);
        List<FcUnit> fcUnits = estateService.selectUnitByBuildingCode(buildingCode);
        System.out.println("---------------------");
        System.out.println(fcUnits);
        return JSONObject.toJSONString(new ReturnObject(fcUnits));
    }

    @RequestMapping("/estate/selectCellByUnitCode")
    public String selectCellByUnitCode(String unitCode){
        System.out.println("unitCode:" + unitCode);
        List<FcCell> fcCells = estateService.selectCellByUnitCode(unitCode);
        System.out.println("---------------------");
        System.out.println(fcCells);
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }

    @RequestMapping("/estate/selectEstate")
    public String selectEstate(String company) {
        System.out.println("selectEstate");
        List<FcEstate> fcEstates = estateService.selectEstate(company);
        return JSONObject.toJSONString(new ReturnObject(fcEstates));
    }
}
