def compute(x,y)
      xarray = x.chars
      yarray = y.chars

      xarray.zip(yarray).delete_if {| a, b | a == nil || b == nil }.count { | a, b | a != b }

    end