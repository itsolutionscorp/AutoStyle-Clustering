class Complement
  DNA_TO_RNA = {G: 'C', C: 'G', T: 'A', A: 'U'}
  def self.of_dna(strand)
    rna = strand.chars.collect! { |char| DNA_TO_RNA[char.upcase.to_sym] }
    rna.join
  end

  def self.of_rna(strand)
    dna = strand.chars.collect! { |char| DNA_TO_RNA.key(char).to_s }
    dna.join
  end
end
