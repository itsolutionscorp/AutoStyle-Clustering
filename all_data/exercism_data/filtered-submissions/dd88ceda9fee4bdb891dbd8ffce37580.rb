def compute(code1, code2)


    distance = 0


    if(code1 != code2)
      for i in 0..[code1.length, code2.length].min
        if(code1[i] != code2[i])
          distance += 1
        end
      end
    end
    distance
   end