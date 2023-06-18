
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import Model.*;

public class LibrariePersistentaTest {
    @Test
    public void cautareLibrarieTest() throws SQLException {

        LibrariePersistenta librariePersistenta = new LibrariePersistenta();
        Librarie l = librariePersistenta.cautareLibrarie(5);

        assertNotNull(l);
        assertEquals(5, l.getIdLibrarie());
    }}