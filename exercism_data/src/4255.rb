class Hamming 

  def compute (str1, str2)

    if str1.length != str2.length
      return 0
    end

    sum = 0

    for i in 0..str1.length

      if str1[i] != str2[i]
        sum +=1
      end
    
    end

    return sum

  end

end
