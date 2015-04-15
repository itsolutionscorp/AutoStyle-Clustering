class Complement

  DICTIONARY = {C: "G", G: "C", T: "A", A: "U"}

  def self.of_dna(strand)
    @strand = strand
    self.translate_to_rna
  end

  def self.of_rna(strand)
    @strand = strand
    self.translate_to_dna
  end

  def self.translate_to_rna
    @strand.chars.map {|nucleotide| DICTIONARY[nucleotide.to_sym]}.join
  end

  def self.translate_to_dna
    @strand.chars.map {|nucleotide| DICTIONARY.key(nucleotide)}.join
  end


end
