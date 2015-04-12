class Hamming
  def compute(strand_1, strand_2)
    distance = 0
    strand_1.chars.zip(strand_2.chars).each { |c| distance += 1 if c.first != c.last }
    distance
  end
end
