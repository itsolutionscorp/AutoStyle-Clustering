class Complement

  def self.of_dna(base_chain)
    new(base_chain).of_dna
  end

  def self.of_rna(base_chain)
    new(base_chain).of_rna
  end

  def initialize(base_chain)
    @base_chain = base_chain
    @@rna_complements = { "C" => "G", "G" => "C", "U" => "A", "A" => "T" }.freeze
    @@dna_complements = { "C" => "G", "G" => "C", "T" => "A", "A" => "U" }.freeze
  end

  def of_dna
    @base_chain.gsub(/[CGTA]/, @@dna_complements)
  end

  def of_rna
    @base_chain.gsub(/[CGUA]/, @@rna_complements)
  end

end
