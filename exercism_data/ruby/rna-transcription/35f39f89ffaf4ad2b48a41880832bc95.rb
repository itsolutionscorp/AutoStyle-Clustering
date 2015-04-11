class DNA
  PAIRS = {'C' => 'C', 'T' => 'U', 'A' => 'A', 'G' => 'G'}
  def initialize(nucleotide_chain)
    @chain = nucleotide_chain
  end

  def to_rna
    @chain.split("").map{|nucleotide| PAIRS[nucleotide]}.join
  end
end
