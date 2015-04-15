class Hamming
  def self.compute(strand1, strand2)
    array1 = strand1.chars
    array2 = strand2.chars
    difference = 0
    array1.each_with_index do |char, index|
      if array2[index] != char
        difference += 1
      end
    end
    difference
  end
end
