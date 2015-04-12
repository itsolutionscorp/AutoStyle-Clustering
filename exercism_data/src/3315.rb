class Hamming
  def compute(string1,string2)
    sum = 0
    i = 0

    until i > string1.length
      sum += 1 if string1[i] != string2[i]
      i+=1
    end

    return sum
  end
end
