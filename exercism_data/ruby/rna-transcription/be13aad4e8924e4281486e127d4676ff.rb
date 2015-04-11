module Complement

  def self.of_dna(strand_code)
    DnaStrand.new(strand_code).complementary.join
  end

  def self.of_rna(strand_code)
    RnaStrand.new(strand_code).complementary.join
  end

end

class Strand

  NUCLEOTIDE_CORRESPONDENCES = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def initialize(strand_code)
    @strand_code = strand_code
  end

  def strand_code
    @strand_code.split("")
  end

  def complementary
    strand_code.map { |elem| nucleotide_correspondences[elem] }
  end

end

class DnaStrand < Strand

  def nucleotide_correspondences
    self.class::NUCLEOTIDE_CORRESPONDENCES
  end

end

class RnaStrand < Strand

  def nucleotide_correspondences
    self.class::NUCLEOTIDE_CORRESPONDENCES.invert
  end

end
