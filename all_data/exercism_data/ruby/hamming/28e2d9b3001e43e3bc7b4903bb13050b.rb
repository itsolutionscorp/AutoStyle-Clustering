class Hamming

  class InvalidStrandError < StandardError
    def message
      "Strands must be same length and composed of only [ACGT]"
    end
  end

  def self.compute strand_one, strand_two
    fail InvalidStrandError if strand_one.length != strand_two.length
    fail InvalidStrandError unless valid_strand?(strand_one) && valid_strand?(strand_two)
    distance = 0
    strand_one, strand_two = strand_one.chars, strand_two.chars
    strand_one.each_with_index do |nucleotide, i|
       distance += 1 unless nucleotide == strand_two[i]
    end
    distance
  end

  def self.valid_strand?(strand)
    strand =~ /[ACGT]/i
  end

end
