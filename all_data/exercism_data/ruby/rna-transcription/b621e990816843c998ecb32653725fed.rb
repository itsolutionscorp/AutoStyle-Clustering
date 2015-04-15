class Complement

  def self.of_dna(strand)
    translator = Translator.new(strand)
    translator.translate_to_rna
  end

  def self.of_rna(strand)
    translator = Translator.new(strand)
    translator.translate_to_dna
  end

end

class Translator
  DICTIONARY = {C: "G", G: "C", T: "A", A: "U"}

  def initialize(strand)
    @strand = strand
  end

  def translate_to_rna
    @strand.chars.map {|nucleotide| DICTIONARY[nucleotide.to_sym]}.join
  end

  def translate_to_dna
    @strand.chars.map {|nucleotide| DICTIONARY.key(nucleotide)}.join
  end

end
