class Hamming
  def self.compute(strand1, strand2)
    arr1 = strand1.chars.to_a
    arr2 = strand2.chars.to_a
    i = 0
    while arr1.length > 0
      char1 = arr1.shift
      char2 = arr2.shift
      i += 1 if char1 != char2
    end
    return i
  end
end
