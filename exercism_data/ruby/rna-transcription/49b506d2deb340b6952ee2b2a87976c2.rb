class Complement
  DNA_TO_RNA = {G: 'C', C: 'G', T: 'A', A: 'U'}
  def self.of_dna(strand)
    rna = ""
    strand.each_char { |char| rna << Complement::DNA_TO_RNA[char.upcase.to_sym] }
    rna
  end

  def self.of_rna(strand)
    dna = ""
    strand.each_char { |char| dna << Complement::DNA_TO_RNA.key(char).to_s }
    dna
  end
end
