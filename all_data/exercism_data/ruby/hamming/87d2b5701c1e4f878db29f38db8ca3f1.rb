class Hamming
  def self.compute(strand_1, strand_2)
    letter_pairs = merge(strand_1, strand_2)
    letter_pairs.count{ |letter_pair| letters_differ?(letter_pair) }
  end

  def self.merge(strand_1, strand_2)
    strand_1, strand_2 = strand_2, strand_1 if strand_2.length < strand_1.length
    strand_1.split("").zip(strand_2.split(""))
  end

  def self.letters_differ?(letter_pair)
    letter_pair[0] != letter_pair[1] ? true : false
  end
end
