/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reportestpv;

import java.util.ArrayList;

/**
 *
 * @author TPV
 */
public class ItemMes {
    
    private Integer orden;
    private String label;
    private String tipo_dato;
    private Integer val_1, val_2, val_3, val_4, val_5, val_6, val_7, val_8, val_9, val_10;
    private Integer val_11, val_12, val_13, val_14, val_15, val_16, val_17, val_18, val_19, val_20;
    private Integer val_21, val_22, val_23, val_24, val_25, val_26, val_27, val_28, val_29, val_30, val_31;
    private Integer total;
    
    public ItemMes() {
        orden = 0;
        label = "";
        tipo_dato = "";
        val_1 = val_2 = val_3 = val_4 = val_5 = val_6 = val_7 = val_8 = val_9 = val_10 = 0;
        val_11 = val_12 = val_13 = val_14 = val_15 = val_16 = val_17 = val_18 = val_19 = val_20 = 0;
        val_21 = val_22 = val_23 = val_24 = val_25 = val_26 = val_27 = val_28 = val_29 = val_30 = val_31 = 0;
        total = 0;
    }
        
    public ItemMes(ItemMes report){
        orden =  report.getOrden();
        label =  report.getLabel();
        tipo_dato = report.getTipo_dato();
        val_1 =  report.getVal_1();
        val_2 =  report.getVal_2();
        val_3 =  report.getVal_3();
        val_4 =  report.getVal_4();
        val_5 =  report.getVal_5();
        val_6 =  report.getVal_6();
        val_7 =  report.getVal_7();
        val_8 =  report.getVal_8();
        val_9 =  report.getVal_9();
        val_10 =  report.getVal_10();
        val_11 =  report.getVal_11();
        val_12 =  report.getVal_12();
        val_13 =  report.getVal_13();
        val_14 =  report.getVal_14();
        val_15 =  report.getVal_15();
        val_16 =  report.getVal_16();
        val_17 =  report.getVal_17();
        val_18 =  report.getVal_18();
        val_19 =  report.getVal_19();
        val_20 =  report.getVal_20();
        val_21 =  report.getVal_21();
        val_22 =  report.getVal_22();
        val_23 =  report.getVal_23();
        val_24 =  report.getVal_24();
        val_25 =  report.getVal_25();
        val_26 =  report.getVal_26();
        val_27 =  report.getVal_27();
        val_28 =  report.getVal_28();
        val_29 =  report.getVal_29();
        val_30 =  report.getVal_30();
        val_31 =  report.getVal_31();
        total = 0;
    }
    
    public void setValue(Integer val){
        if (val == 1) val_1 = val;
        if (val == 2) val_2 = val;
        if (val == 3) val_3 = val;
        if (val == 4) val_4 = val;
        if (val == 5) val_5 = val;
        if (val == 6) val_6 = val;
        if (val == 7) val_7 = val;
        if (val == 8) val_8 = val;
        if (val == 9) val_9 = val;
        if (val == 10) val_10 = val;
        if (val == 11) val_11 = val;
        if (val == 12) val_12 = val;
        if (val == 13) val_13 = val;
        if (val == 14) val_14 = val;
        if (val == 15) val_15 = val;
        if (val == 16) val_16 = val;
        if (val == 17) val_17 = val;
        if (val == 18) val_18 = val;
        if (val == 19) val_19 = val;
        if (val == 20) val_20 = val;
        if (val == 21) val_21 = val;
        if (val == 22) val_22 = val;
        if (val == 23) val_23 = val;
        if (val == 24) val_24 = val;
        if (val == 25) val_25 = val;
        if (val == 26) val_26 = val;
        if (val == 27) val_27 = val;
        if (val == 28) val_28 = val;
        if (val == 29) val_29 = val;
        if (val == 30) val_30 = val;
        if (val == 31) val_31 = val;
    }
    
    public void setValue(Integer dia, Integer val){
        if (dia == 1) val_1 = val;
        if (dia == 2) val_2 = val;
        if (dia == 3) val_3 = val;
        if (dia == 4) val_4 = val;
        if (dia == 5) val_5 = val;
        if (dia == 6) val_6 = val;
        if (dia == 7) val_7 = val;
        if (dia == 8) val_8 = val;
        if (dia == 9) val_9 = val;
        if (dia == 10) val_10 = val;
        if (dia == 11) val_11 = val;
        if (dia == 12) val_12 = val;
        if (dia == 13) val_13 = val;
        if (dia == 14) val_14 = val;
        if (dia == 15) val_15 = val;
        if (dia == 16) val_16 = val;
        if (dia == 17) val_17 = val;
        if (dia == 18) val_18 = val;
        if (dia == 19) val_19 = val;
        if (dia == 20) val_20 = val;
        if (dia == 21) val_21 = val;
        if (dia == 22) val_22 = val;
        if (dia == 23) val_23 = val;
        if (dia == 24) val_24 = val;
        if (dia == 25) val_25 = val;
        if (dia == 26) val_26 = val;
        if (dia == 27) val_27 = val;
        if (dia == 28) val_28 = val;
        if (dia == 29) val_29 = val;
        if (dia == 30) val_30 = val;
        if (dia == 31) val_31 = val;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getVal_1() {
        return val_1;
    }

    public void setVal_1(Integer val_1) {
        this.val_1 = val_1;
    }

    public Integer getVal_2() {
        return val_2;
    }

    public void setVal_2(Integer val_2) {
        this.val_2 = val_2;
    }

    public Integer getVal_3() {
        return val_3;
    }

    public void setVal_3(Integer val_3) {
        this.val_3 = val_3;
    }

    public Integer getVal_4() {
        return val_4;
    }

    public void setVal_4(Integer val_4) {
        this.val_4 = val_4;
    }

    public Integer getVal_5() {
        return val_5;
    }

    public void setVal_5(Integer val_5) {
        this.val_5 = val_5;
    }

    public Integer getVal_6() {
        return val_6;
    }

    public void setVal_6(Integer val_6) {
        this.val_6 = val_6;
    }

    public Integer getVal_7() {
        return val_7;
    }

    public void setVal_7(Integer val_7) {
        this.val_7 = val_7;
    } 

    public Integer getVal_8() {
        return val_8;
    }

    public void setVal_8(Integer val_8) {
        this.val_8 = val_8;
    }

    public Integer getVal_9() {
        return val_9;
    }

    public void setVal_9(Integer val_9) {
        this.val_9 = val_9;
    }

    public Integer getVal_10() {
        return val_10;
    }

    public void setVal_10(Integer val_10) {
        this.val_10 = val_10;
    }

    public Integer getVal_11() {
        return val_11;
    }

    public void setVal_11(Integer val_11) {
        this.val_11 = val_11;
    }

    public Integer getVal_12() {
        return val_12;
    }

    public void setVal_12(Integer val_12) {
        this.val_12 = val_12;
    }

    public Integer getVal_13() {
        return val_13;
    }

    public void setVal_13(Integer val_13) {
        this.val_13 = val_13;
    }

    public Integer getVal_14() {
        return val_14;
    }

    public void setVal_14(Integer val_14) {
        this.val_14 = val_14;
    }

    public Integer getVal_15() {
        return val_15;
    }

    public void setVal_15(Integer val_15) {
        this.val_15 = val_15;
    }

    public Integer getVal_16() {
        return val_16;
    }

    public void setVal_16(Integer val_16) {
        this.val_16 = val_16;
    }

    public Integer getVal_17() {
        return val_17;
    }

    public void setVal_17(Integer val_17) {
        this.val_17 = val_17;
    }

    public Integer getVal_18() {
        return val_18;
    }

    public void setVal_18(Integer val_18) {
        this.val_18 = val_18;
    }

    public Integer getVal_19() {
        return val_19;
    }

    public void setVal_19(Integer val_19) {
        this.val_19 = val_19;
    }

    public Integer getVal_20() {
        return val_20;
    }

    public void setVal_20(Integer val_20) {
        this.val_20 = val_20;
    }

    public Integer getVal_21() {
        return val_21;
    }

    public void setVal_21(Integer val_21) {
        this.val_21 = val_21;
    }

    public Integer getVal_22() {
        return val_22;
    }

    public void setVal_22(Integer val_22) {
        this.val_22 = val_22;
    }

    public Integer getVal_23() {
        return val_23;
    }

    public void setVal_23(Integer val_23) {
        this.val_23 = val_23;
    }

    public Integer getVal_24() {
        return val_24;
    }

    public void setVal_24(Integer val_24) {
        this.val_24 = val_24;
    }

    public Integer getVal_25() {
        return val_25;
    }

    public void setVal_25(Integer val_25) {
        this.val_25 = val_25;
    }

    public Integer getVal_26() {
        return val_26;
    }

    public void setVal_26(Integer val_26) {
        this.val_26 = val_26;
    }

    public Integer getVal_27() {
        return val_27;
    }

    public void setVal_27(Integer val_27) {
        this.val_27 = val_27;
    }

    public Integer getVal_28() {
        return val_28;
    }

    public void setVal_28(Integer val_28) {
        this.val_28 = val_28;
    }

    public Integer getVal_29() {
        return val_29;
    }

    public void setVal_29(Integer val_29) {
        this.val_29 = val_29;
    }

    public Integer getVal_30() {
        return val_30;
    }

    public void setVal_30(Integer val_30) {
        this.val_30 = val_30;
    }

    public Integer getVal_31() {
        return val_31;
    }

    public void setVal_31(Integer val_31) {
        this.val_31 = val_31;
    }
    
    public void sumVal_1(Integer val) {
        val_1 += val;
    }
    
    public void sumVal_2(Integer val) {
        val_2 += val;
    }
    
    public void sumVal_3(Integer val) {
        val_3 += val;
    }
    
    public void sumVal_4(Integer val) {
        val_4 += val;
    }
    
    public void sumVal_5(Integer val) {
        val_5 += val;
    }
    
    public void sumVal_6(Integer val) {
        val_6 += val;
    }
    
    public void sumVal_7(Integer val) {
        val_7 += val;
    }
    
    public void sumVal_8(Integer val) {
        val_8 += val;
    }
    
    public void sumVal_9(Integer val) {
        val_9 += val;
    }
    
    public void sumVal_10(Integer val) {
        val_10 += val;
    }
    
    public void sumVal_11(Integer val) {
        val_11 += val;
    }
    
    public void sumVal_12(Integer val) {
        val_12 += val;
    }
    
    public void sumVal_13(Integer val) {
        val_13 += val;
    }
    
    public void sumVal_14(Integer val) {
        val_14 += val;
    }
    
    public void sumVal_15(Integer val) {
        val_15 += val;
    }
    
    public void sumVal_16(Integer val) {
        val_16 += val;
    }
    
    public void sumVal_17(Integer val) {
        val_17 += val;
    }
    
    public void sumVal_18(Integer val) {
        val_18 += val;
    }
    
    public void sumVal_19(Integer val) {
        val_19 += val;
    }
    
    public void sumVal_20(Integer val) {
        val_20 += val;
    }
    
    public void sumVal_21(Integer val) {
        val_21 += val;
    }
    
    public void sumVal_22(Integer val) {
        val_22 += val;
    }
    
    public void sumVal_23(Integer val) {
        val_23 += val;
    }
    
    public void sumVal_24(Integer val) {
        val_24 += val;
    }
    
    public void sumVal_25(Integer val) {
        val_25 += val;
    }
    
    public void sumVal_26(Integer val) {
        val_26 += val;
    }
    
    public void sumVal_27(Integer val) {
        val_27 += val;
    }
    
    public void sumVal_28(Integer val) {
        val_28 += val;
    }
    
    public void sumVal_29(Integer val) {
        val_29 += val;
    }
    
    public void sumVal_30(Integer val) {
        val_30 += val;
    }
    
    public void sumVal_31(Integer val) {
        val_31 += val;
    }
 
    public void println(){
        System.out.println("["+tipo_dato+"] "+
                           orden.toString()+" - "+
                           label+": "+
                           val_1+" "+
                           val_2+" "+
                           val_3+" "+
                           val_5+" "+
                           val_6+" "+
                           val_7+" "+
                           val_8+" "+
                           val_9+" "+
                           val_10+" "+
                           val_11+" "+
                           val_12+" "+
                           val_13+" "+
                           val_14+" "+
                           val_15+" "+
                           val_16+" "+
                           val_17+" "+
                           val_18+" "+
                           val_19+" "+
                           val_21+" "+
                           val_22+" "+
                           val_23+" "+
                           val_24+" "+
                           val_25+" "+
                           val_26+" "+
                           val_27+" "+
                           val_28+" "+
                           val_29+" "+
                           val_30+" "+
                           val_31+" ["+
                           total+" ]");
    }
    
    public void calcularTotal(ArrayList report){
        
        for(int j = 0; j < report.size() ;j++){
            if (((ItemMes)report.get(j)).getTipo_dato().equals("CONTABLE")){
                sumVal_1( ((ItemMes)report.get(j)).getVal_1() );
                sumVal_2( ((ItemMes)report.get(j)).getVal_2() );
                sumVal_3( ((ItemMes)report.get(j)).getVal_3() );
                sumVal_4( ((ItemMes)report.get(j)).getVal_4() );
                sumVal_5( ((ItemMes)report.get(j)).getVal_5() );
                sumVal_6( ((ItemMes)report.get(j)).getVal_6() );
                sumVal_7( ((ItemMes)report.get(j)).getVal_7() );
                sumVal_8( ((ItemMes)report.get(j)).getVal_8() );
                sumVal_9( ((ItemMes)report.get(j)).getVal_9() );
                sumVal_10( ((ItemMes)report.get(j)).getVal_10() );
                sumVal_11( ((ItemMes)report.get(j)).getVal_11() );
                sumVal_12( ((ItemMes)report.get(j)).getVal_12() );
                sumVal_13( ((ItemMes)report.get(j)).getVal_13() );
                sumVal_14( ((ItemMes)report.get(j)).getVal_14() );
                sumVal_15( ((ItemMes)report.get(j)).getVal_15() );
                sumVal_16( ((ItemMes)report.get(j)).getVal_16() );
                sumVal_17( ((ItemMes)report.get(j)).getVal_17() );
                sumVal_18( ((ItemMes)report.get(j)).getVal_18() );
                sumVal_19( ((ItemMes)report.get(j)).getVal_19() );
                sumVal_20( ((ItemMes)report.get(j)).getVal_20() );
                sumVal_21( ((ItemMes)report.get(j)).getVal_21() );
                sumVal_22( ((ItemMes)report.get(j)).getVal_22() );
                sumVal_23( ((ItemMes)report.get(j)).getVal_23() );
                sumVal_24( ((ItemMes)report.get(j)).getVal_24() );
                sumVal_25( ((ItemMes)report.get(j)).getVal_25() );
                sumVal_26( ((ItemMes)report.get(j)).getVal_26() );
                sumVal_27( ((ItemMes)report.get(j)).getVal_27() );
                sumVal_28( ((ItemMes)report.get(j)).getVal_28() );
                sumVal_29( ((ItemMes)report.get(j)).getVal_29() );
                sumVal_30( ((ItemMes)report.get(j)).getVal_30() );
                sumVal_31( ((ItemMes)report.get(j)).getVal_31() );
            }
            ((ItemMes)report.get(j)).setNull();    
        }  
    }
    
    
    public void calcularDiferencia(ItemMes report){
        val_1 -=  report.getVal_1();
        val_2 -=  report.getVal_2();
        val_3 -=  report.getVal_3();
        val_4 -=  report.getVal_4();
        val_5 -=  report.getVal_5();
        val_6 -=  report.getVal_6();
        val_7 -=  report.getVal_7();
        val_8 -=  report.getVal_8();
        val_9 -=  report.getVal_9();
        val_10 -=  report.getVal_10();
        val_11 -=  report.getVal_11();
        val_12 -=  report.getVal_12();
        val_13 -=  report.getVal_13();
        val_14 -=  report.getVal_14();
        val_15 -=  report.getVal_15();
        val_16 -=  report.getVal_16();
        val_17 -=  report.getVal_17();
        val_18 -=  report.getVal_18();
        val_19 -=  report.getVal_19();
        val_20 -=  report.getVal_20();
        val_21 -=  report.getVal_21();
        val_22 -=  report.getVal_22();
        val_23 -=  report.getVal_23();
        val_24 -=  report.getVal_24();
        val_25 -=  report.getVal_25();
        val_26 -=  report.getVal_26();
        val_27 -=  report.getVal_27();
        val_28 -=  report.getVal_28();
        val_29 -=  report.getVal_29();
        val_30 -=  report.getVal_30();
        val_31 -=  report.getVal_31();
    }
    
    public void setNull(){
        if (val_1 == 0) val_1 = null;
        if (val_2 == 0) val_2 = null;
        if (val_3 == 0) val_3 = null;
        if (val_4 == 0) val_4 = null;
        if (val_5 == 0) val_5 = null;
        if (val_6 == 0) val_6 = null;
        if (val_7 == 0) val_7 = null;
        if (val_8 == 0) val_8 = null;
        if (val_9 == 0) val_9 = null;
        if (val_10 == 0) val_10 = null;
        if (val_11 == 0) val_11 = null;
        if (val_12 == 0) val_12 = null;
        if (val_13 == 0) val_13 = null;
        if (val_14 == 0) val_14 = null;
        if (val_15 == 0) val_15 = null;
        if (val_16 == 0) val_16 = null;
        if (val_17 == 0) val_17 = null;
        if (val_18 == 0) val_18 = null;
        if (val_19 == 0) val_19 = null;
        if (val_20 == 0) val_20 = null;
        if (val_21 == 0) val_21 = null;
        if (val_22 == 0) val_22 = null;
        if (val_23 == 0) val_23 = null;
        if (val_24 == 0) val_24 = null;
        if (val_25 == 0) val_25 = null;
        if (val_26 == 0) val_26 = null;
        if (val_27 == 0) val_27 = null;
        if (val_28 == 0) val_28 = null;
        if (val_29 == 0) val_29 = null;
        if (val_30 == 0) val_30 = null;
        if (val_31 == 0) val_31 = null;
     
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public void sumTotal() {
        if (tipo_dato.equals("CONTABLE")){
            this.total = val_1 + val_2 + val_3 + val_4 + val_5 + val_6 + val_7 + val_8 + val_9 + val_10 +
            val_11 + val_12 + val_13 + val_14 + val_15 + val_16 + val_17 + val_18 + val_19 + val_20 +
            val_21 + val_22 + val_23 + val_24 + val_25 + val_26 + val_27 + val_28 + val_29 + val_30 + val_31;
        }else {
            this.total = null;
        }
    }

    public String getTipo_dato() {
        return tipo_dato;
    }

    public void setTipo_dato(String tipo_dato) {
        this.tipo_dato = tipo_dato;
    }
    
}

    
    
    

