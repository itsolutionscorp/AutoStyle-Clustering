class Hamming
  def self.compute(firstVal, secondVal)
    differences = 0
    firstVal.each_char.with_index do |char, i|
      if char != secondVal[i]
        differences += 1
      end
    end
    differences
  end
end
