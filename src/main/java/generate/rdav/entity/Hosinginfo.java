package generate.rdav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName hosinginfo
 */
@TableName(value ="hosinginfo")
@Data
public class Hosinginfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String rent;

    private String position;

    private String housetype;

    private String buildingOrientation;

    private String decorateSituation;

    private String platformWebsite;

    private String phone;

    private String area;

    private String floor;

    private String maintenanceDate;
    private int statitionNum;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}