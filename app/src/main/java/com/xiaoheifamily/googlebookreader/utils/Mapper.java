package com.xiaoheifamily.googlebookreader.utils;

import com.xiaoheifamily.googlebookreader.functional.Function1;

import java.util.ArrayList;
import java.util.List;

public final class Mapper {

    public static <TInput, TOutput> TOutput map(TInput input, Function1<TInput, TOutput> mapper) {
        return mapper.call(input);
    }

    public static <TInput, TOutput> List<TOutput> mapList(List<TInput> inputs, Function1<TInput, TOutput> mapper) {

        List<TOutput> outputs = new ArrayList<>();

        for (TInput input : inputs) {
            outputs.add(mapper.call(input));
        }

        return outputs;
    }
}