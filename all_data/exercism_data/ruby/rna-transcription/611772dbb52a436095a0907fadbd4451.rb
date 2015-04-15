class DNA
  def initialize(rna_chain)
    @rna_chain = rna_chain
  end

  def to_rna
    dna_chain = @rna_chain.each_char.map do |rna_char|
      dna_char_for_rna(rna_char)
    end
    dna_chain.join
  end

  private

  def dna_char_for_rna(rna_char)
    { 'T' => 'U' }.fetch(rna_char, rna_char)
  end
end
