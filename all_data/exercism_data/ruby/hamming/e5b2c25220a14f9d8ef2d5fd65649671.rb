class Hamming
  def self.compute(a,b)
    "Strings are not of the same length" if a.length != b.length
    distance = 0
    a.chars.each_with_index {|char, idx| distance += 1 if char != b[idx] }
    distance
  end
end
