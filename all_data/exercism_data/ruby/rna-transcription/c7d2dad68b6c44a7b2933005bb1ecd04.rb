class DNA
  attr_reader :snippet
  def initialize(snippet)
    @snippet = snippet
  end

  def to_rna
    snippet.tr("CGTA", "GCAU")
  end
end

class RNA
  attr_reader :snippet
  def initialize(snippet)
    @snippet = snippet
  end

  def to_dna
    snippet.tr("CGAU", "GCTA")
  end
end

class Complement

  def self.of_dna(snippet)
    DNA.new(snippet).to_rna
  end

  def self.of_rna(snippet)
    RNA.new(snippet).to_dna
  end
end
