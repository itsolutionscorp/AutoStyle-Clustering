class Hamming

  def self.compute(strand1, strand2)
    diff = 0
    array1 = strand1.split("")
    array2 = strand2.split("")
    array1.each_with_index { |x, i| diff += 1 unless x == array2[i] }
    diff
  end

end
