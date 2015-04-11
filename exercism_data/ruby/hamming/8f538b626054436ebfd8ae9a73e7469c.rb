class Hamming

  def self.compute(one, two)
    count = 0
    result = 0
    one.length.times do
      result += 1 if one[count] != two[count]
      count +=1
    end
    result
  end

end
