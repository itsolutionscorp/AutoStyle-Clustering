class Hamming
  def self.compute(strand_one, strand_two)
    if strand_two.length < strand_one.length
      strand_one,strand_two = strand_two,strand_one
    end
    strand_one.chars.each_with_index.count do |char, index|
      char != strand_two[index]
    end
  end
end
