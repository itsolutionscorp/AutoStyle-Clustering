class Hamming

  def compute(code1, code2)

    # if the strings match then the distance is 0
    distance = 0

    #count the differences at each string indice to compute hamming distance
    if(code1 != code2)
      for i in 0..[code1.length, code2.length].min
        if(code1[i] != code2[i])
          distance += 1
        end
      end
    end
    distance
   end
end
