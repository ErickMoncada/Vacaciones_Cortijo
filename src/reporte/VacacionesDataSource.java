
package reporte;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author JorgeLPR
 */
public class VacacionesDataSource implements JRDataSource{

    private final Object [][] listadoPaises;
    private int index; 
    
    public VacacionesDataSource(Object[][] datos){
        index = -1;
        listadoPaises = datos;
    }
    
    @Override
    public boolean next() throws JRException {
        index++;
        return (index < listadoPaises.length);
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
        Object value = null;
        
        String fieldName = jrf.getName();
        
        switch(fieldName){
            
            case "Nombre":
                value = listadoPaises[index][0];
            break;
            
            case "CentroCosto":
                value = listadoPaises[index][1];                
            break;
            
            case "year":
                value = listadoPaises[index][2];                
            break;
            
            case "DiaStart":
                value = listadoPaises[index][3];                
            break;
            
            case "MesStart":
                value = listadoPaises[index][4];                
            break;
            
            case "yearStart":
                value = listadoPaises[index][5];                
            break;
            
            case "FechaIngreso":
                value = listadoPaises[index][6];                
            break;
            
            case "YearsLaburar":
                value = listadoPaises[index][7];                
            break;
            
            case "DiasVacaciones":
                value = listadoPaises[index][8];                
            break;
            
            case "FechaInicio":
                value = listadoPaises[index][9];                
            break;
            
            case "FechaFinal":
                value = listadoPaises[index][10];                
            break;
            
            case "FechaRegreso":
                value = listadoPaises[index][11];                
            break;
        }
        
        return value;
    
    }
    
    public static JRDataSource getDataSource(Object[][] datos){
        return new VacacionesDataSource(datos);
    }
    
}
