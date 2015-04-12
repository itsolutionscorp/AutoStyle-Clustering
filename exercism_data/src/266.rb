def compute(x,y)
      xarray = x.split(//)
      yarray = y.split(//)

      xarray.zip(yarray).delete_if {| a, b | a == nil || b == nil }.count { | a, b | a != b }

    end