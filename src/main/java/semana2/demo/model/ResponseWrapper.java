package semana2.demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" }) 
public class ResponseWrapper<T> {
    
    private String estado;
    private int cantidad;
    private LocalDateTime fecha;
    private List<T> data;

    public ResponseWrapper(String estado, int cantidad, List<T> data) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return fecha;
    }

    public void setTimestamp(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return estado;
    }

    public void setStatus(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
