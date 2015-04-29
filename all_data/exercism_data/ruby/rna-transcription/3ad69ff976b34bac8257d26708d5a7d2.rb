class Complement
  DNA_TO_RNA = {G: 'C', C: 'G', T: 'A', A: 'U'}
  def self.of_dna(dna_strand)
    rna_complement = dna_strand.chars.collect! { |char| DNA_TO_RNA[char.upcase.to_sym] } .join
  end

  def self.of_rna(rna_strand)
    dna_complement = rna_strand.chars.collect! { |char| DNA_TO_RNA.key(char).to_s } .join
  end
end
