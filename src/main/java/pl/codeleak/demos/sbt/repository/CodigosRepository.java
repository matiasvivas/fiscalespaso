package pl.codeleak.demos.sbt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.codeleak.demos.sbt.enumeradores.Distritos;
import pl.codeleak.demos.sbt.model.CierreMesa;

public interface CodigosRepository extends CrudRepository<CierreMesa,Integer> {
        @Query(value= "from CierreMesa where fechaHoraCierre is not null and cantidadVotosLibertadAvanza is not null and username =:username")
        List<CierreMesa> obtenerMisCierresCargados(@Param("username") String username);

        @Query(value= "from CierreMesa where fechaHoraCierre is not null and cantidadVotosLibertadAvanza is not null and username =:username and distrito =:distrito and numeroCircuito =:numeroCircuito and numeroSeccion =:numeroSeccion and numeroMesa =:numeroMesa")
        List<CierreMesa> validarQueNoSeRepitaElCierreDeMesa(@Param("username") String username, @Param("distrito") Distritos distrito, @Param("numeroCircuito") Integer numeroCircuito, @Param("numeroSeccion") Integer numeroSeccion, @Param("numeroMesa") Integer numeroMesa);
        @Query(value= "select sum(cantidadVotosLibertadAvanza) from CierreMesa where fechaHoraCierre is not null and cantidadVotosLibertadAvanza is not null")
        Integer obtenerTotalVotosPositivos();
}
