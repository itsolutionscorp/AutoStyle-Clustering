class Hamming

  def self.compute(one, two)
    res = 0
    one.each_char.each_with_index do |item, index|
      res = res + 1 if two[index] != item and two[index] != nil 
    end 
    res
  end

end
