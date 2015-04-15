def compute(var1, var2)

    if var1.length > var2.length
      short_var = var2
    else
      short_var = var1
    end

    i = 0
    value = 0

    while i < short_var.length
      if var1[i] != var2[i]
        value += 1
      end
       i += 1

    end
    value
  end