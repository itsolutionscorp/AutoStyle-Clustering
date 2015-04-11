class Complement
  def self.of_dna nucleotides
    DNA.new(nucleotides).complement
  end

  def self.of_rna nucleotides
    RNA.new(nucleotides).complement
  end
end

class Strand
  attr_reader :nucleotides

  def initialize nucleotides
    @nucleotides = nucleotides
  end

  def complement
    nucleotides.chars.map { |nucleotide| self.class::COMPLEMENT[nucleotide] }.join
  end
end

class DNA < Strand
  COMPLEMENT = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
end

class RNA < Strand
  COMPLEMENT = DNA::COMPLEMENT.invert
end
