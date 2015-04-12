class Hamming
  def compute(strand_1, strand_2)
    strand_one = strand_1.chars.take(strand_2.length)
    strand_two = strand_2.chars.take(strand_1.length)

    pairs = strand_one.zip(strand_two)

    difference = pairs.count do | letter_1, letter_2 |
      letter_1 != letter_2
    end

    difference
  end
end
