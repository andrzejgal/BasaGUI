/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pErrorCodes;

/**
 *
 * @author dell
 */
public enum ErrorCodes {
    TestDataBaseError,
    TestDataBaseRssetClosing,
    ClassNotFoundError,
    DataBaseInstantiationExceptionError,
    DataBaseIllegalAccessExceptionError,
    InsertEPCError,
    InsertEPCInConnectionNull,
    CreateTableInputConnectionNull,
    CreateTableError,
    CreateTableErrorStatementClosing,
    ReadEPCsError,
    ReadEPCsInputConnectionNull,
    ReadEPCsRssetClosing,
    ReadEPCsStatementClosing,
    ReadLabelStatusSQLExceptionError,
    ReadLabelStatusInputConnectionNull,
    ReadLabelStatusRssetClosing,
    ReadLabelStatusPrepareStatementClosing,
    DropTableInConnectionNull,
    DropTableSQLExceptionError,
    DropTableSQLExceptionStatemmentClosingError,
    CleartableInConnectionNull,
    CleartableSQLException,
    ClearTableStatementclosingError,
    FunctionCloseInDAOfunctionError,
    writeDataBaseToTableClosingconnectionError,
    SingleThreadConnectionClosingError,
    ReadLabelsListConnectionClosingError,
    ReadLabelsListWriteToBaseSelectedRowsError,
    ChangeStatusExceptionError,
    ChangeStatusClosingStatementError
}