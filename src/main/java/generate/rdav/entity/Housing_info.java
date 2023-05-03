package generate.rdav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName housing_info
 */
@TableName(value ="housing_info")
@Data
public class Housing_info implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Double rent;

    /**
     * 
     */
    private Double square;

    /**
     * 
     */
    private Double rentperSquare;

    /**
     * 
     */
    private Integer stationNum;

    /**
     * 
     */
    private String releaseDate;

    /**
     * 
     */
    private String platformWebsite;

    /**
     * 
     */
    private String administrativeDistrict;

    /**
     * 
     */
    private String urbanArea;

    /**
     * 
     */
    private String street;

    /**
     * 
     */
    private String houseType;

    /**
     * 
     */
    private String orientation;

    /**
     *
     */
    private Integer floor;

    /**
     *
     */
     private Integer landlord;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}