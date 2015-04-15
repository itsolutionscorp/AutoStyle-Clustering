class Complement
  DNA = %w(G C T A)
  RNA = %w(C G A U)

  def self.of_dna(dna)
    dna.chars.map{|x| RNA[DNA.index(x)] }.join
  end

  def self.of_rna(rna)
    rna.chars.map{|x| DNA[RNA.index(x)] }.join
  end
end
