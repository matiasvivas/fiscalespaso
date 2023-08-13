package pl.codeleak.demos.sbt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.codeleak.demos.sbt.model.CierreMesa;

public interface CierreMesaRepository extends CrudRepository<CierreMesa,Integer> {
        @Query(value= "from CierreMesa where fechaHoraCierre is not null and cantidadVotosLibertadAvanza is not null and username =:username")
        List<CierreMesa> obtenerMisCierresCargados(@Param("username") String username);
}
