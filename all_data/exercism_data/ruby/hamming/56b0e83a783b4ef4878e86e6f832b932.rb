class Hamming

  def self.compute(strand_1, strand_2)
    strand_1 = strand_1.split(//)
    strand_2 = strand_2.split(//)

    difference = 0

    normalize_length = strand_2.length - strand_1.length
      if narmalize_length > 0 #strand 2 is longer
        strand_2.pop(normalize_length)
      else normalize_length < 0 #strand 1 is longer
        strand_1.pop(normalize_length.abs)
      end

    strand_2.each_with_index do |nucleotide, index|
      difference += 1 if nucleotide != strand_1[index]
    end
    difference
  end
end
