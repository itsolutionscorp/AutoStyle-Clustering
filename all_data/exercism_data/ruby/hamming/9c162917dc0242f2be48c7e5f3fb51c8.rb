class Hamming

  def self.compute(left,right)
    counter = 0
    (0...left.size).each {|i| counter += hamming_number(left[i],right[i]) }
    counter
  end

  def self.hamming_number(left,right)
    left == right ? 0 : 1
  end

end
