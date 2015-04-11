class Complement
  NUCLEOTIDE_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  class << self
    def of_dna strand
      DNA.new(strand).gsub(/[GCTA]/, NUCLEOTIDE_COMPLEMENTS)
    end

    def of_rna strand
      RNA.new(strand).gsub(/[CGAU]/, NUCLEOTIDE_COMPLEMENTS.invert)
    end
  end
end

class DNA < String
  def initialize strand
    strand.chars.map do |n|
      throw "Illegal Nucleotide: #{n}" unless ['G', 'C', 'T', 'A'].include? n
    end
    super
  end
end

class RNA < String
  def initialize strand
    strand.chars.map do |n|
      throw "Illegal Nucleotide: #{n}" unless ['G', 'C', 'U', 'A'].include? n
    end
    super
  end
end
