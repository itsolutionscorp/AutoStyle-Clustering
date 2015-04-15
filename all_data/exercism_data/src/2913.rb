def compute(string1, string2)
    i=0
    counter = 0
    if string1.length <= string2.length
      while i<= string1.length-1
        if string1[i] !=string2[i]
          counter+=1
        end
        i+=1
      end
      counter
    else
      while i<= string2.length-1
        if string2[i] !=string1[i]
          counter+=1
        end
        i+=1
      end
      counter
    end
  end