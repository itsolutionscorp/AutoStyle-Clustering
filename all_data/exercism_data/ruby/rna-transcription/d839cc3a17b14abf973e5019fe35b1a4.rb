class Complement


  def self.of_dna(base_chain)
    new(base_chain).of_dna
  end

  def self.of_rna(base_chain)
    new(base_chain).of_rna
  end

  def initialize(base_chain)
    @base_chain = base_chain
    @rna_complement_hash = { "C" => "G", "G" => "C", "U" => "A", "A" => "T" }
    @dna_complement_hash = { "C" => "G", "G" => "C", "T" => "A", "A" => "U" }
  end

  def of_dna
    complement_chain(@dna_complement_hash)
  end

  def of_rna
    complement_chain(@rna_complement_hash)
  end

  private

  def complement_chain(complement_base)
    @base_chain.chars.map { |base| complement_base[base] }.join
  end

end
