def compute(string_1, string_2)

  	count = 0

    if string_1.length != string_2.length
      return 0
    end

    for posicion in 0..string_1.length
      if string_1[posicion] != string_2[posicion]
        count+=1
      end
    end

    return count
   end