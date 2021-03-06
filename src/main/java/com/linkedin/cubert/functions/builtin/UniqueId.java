/* (c) 2014 LinkedIn Corp. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package com.linkedin.cubert.functions.builtin;

import java.io.IOException;

import org.apache.pig.data.Tuple;

import com.linkedin.cubert.block.BlockSchema;
import com.linkedin.cubert.block.ColumnType;
import com.linkedin.cubert.block.DataType;
import com.linkedin.cubert.functions.Function;
import com.linkedin.cubert.operator.PhaseContext;
import com.linkedin.cubert.operator.PreconditionException;

/**
 * Builtin function that generates a unique id for each process. That is, different
 * mappers or reducers will generate different ids.
 * 
 * @author Maneesh Varshney
 * 
 */
public class UniqueId extends Function
{
    private long id;

    @Override
    public Object eval(Tuple tuple) throws IOException
    {
        return id;
    }

    @Override
    public ColumnType outputSchema(BlockSchema inputSchema) throws PreconditionException
    {
        if (PhaseContext.isIntialized())
            id = PhaseContext.getUniqueId();
        return new ColumnType(null, DataType.LONG);
    }
}
