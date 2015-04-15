class Hamming

  def self.compute(left_strand_string, right_strand_string)
    left_strand = Strand.new(left_strand_string)
    right_strand = Strand.new(right_strand_string)
    left_strand.nucleotides.
      zip(right_strand.nucleotides).
      count do |left_nucleotide, right_nucleotide|
        left_nucleotide != right_nucleotide
      end
  end
end

class Strand

  def initialize(strand_string)
    @strand_string = strand_string
  end

  def nucleotides
    @strand_string.split('')
  end
end
