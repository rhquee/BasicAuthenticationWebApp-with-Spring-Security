package database;

import java.sql.*;



public class DAOImpl {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO USER (USERID,NAME,PASSWORD) VALUES(?,?,?)";

    public static void main( String[] args )
    {
        Person person = new Person( 1, "James", "bond");
        Person person2 = new Person( 2, "Forest", "gump");

        try
        {
            // Create
            insertPerson( person );
            insertPerson( person2 );
            System.out.println( "Persons got inserted sucessfully. This is \"C\" of CRUD " );

        }
        catch ( SQLException e )
        {
            System.out.println( "Exception occured " + e.getMessage() );
        }
        catch ( Exception e )
        {
            System.out.println( "Exception occured " + e.getMessage() );
        }
    }


    private static void insertPerson( Person p ) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = JDBCHelper.getConnection();
            if ( con == null )
            {
                System.out.println( "Error getting the connection. Please check if the DB server is running" );
                return;
            }
            con.setAutoCommit( false );
            ps = con.prepareStatement( INSERT_SQL_QUERY );
            ps.setLong( 1, p.getId() );
            ps.setString( 2, p.getName() );
            ps.setString( 3, p.getPassword() );

            ps.execute();
            System.out.println( "insertPerson => " + ps.toString() );
            con.commit();

        }
        catch ( SQLException e )
        {
            try
            {
                if ( con != null )
                {
                    con.rollback();
                }
            }
            catch ( SQLException e1 )
            {
                throw e1;
            }
            throw e;
        }
        finally
        {
            try
            {
                JDBCHelper.closePrepaerdStatement( ps );
                JDBCHelper.closeConnection( con );
            }
            catch ( SQLException e )
            {
                throw e;
            }
        }

    }

}
