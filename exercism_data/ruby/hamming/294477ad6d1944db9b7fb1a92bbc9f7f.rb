class Hamming

  def self.compute(strand_code_1, strand_code_2)
    strand_1 = Strand.new strand_code_1
    strand_2 = Strand.new strand_code_2

    strand_1.count_dna_differences strand_2
  end

end

class Strand
  attr_reader :strand_code_raw

  def initialize(strand_code_raw)
    @strand_code_raw = strand_code_raw
  end

  def strand_code
    strand_code_raw.split ""
  end

  def count_dna_differences(other)
    differing_elements(other.strand_code).count
  end

  def differing_elements(other)
    strand_code.select.with_index { |elm, index| other[index] && elm != other[index] }
  end
end
