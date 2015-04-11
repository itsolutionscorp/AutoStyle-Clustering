class Complement

  @rna_complements = { "C" => "G",
                        "G" => "C",
                        "U" => "A",
                        "A" => "T" }.freeze

  @dna_complements = @rna_complements.invert.freeze

  class << self
    attr_accessor :dna_complements, :rna_complements
  end

  def self.of_dna(base_chain)
    new(base_chain).of_dna
  end

  def self.of_rna(base_chain)
    new(base_chain).of_rna
  end

  def initialize(base_chain)
    @base_chain = base_chain
  end

  def of_dna
    sub_chain_complements(self.class.dna_complements)
  end

  def of_rna
    sub_chain_complements(self.class.rna_complements)
  end

  private

  def sub_chain_complements(complements_map)
    @base_chain.chars.map { |base| complements_map[base] }.join
  end

end
