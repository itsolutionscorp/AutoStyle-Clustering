class Complement

  @dna_to_rna = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    translate strand, @dna_to_rna
  end

  def self.of_rna(strand)
    translate strand, @dna_to_rna.invert
  end

  private

  def self.translate(strand, translation_hash)
    strand.chars.map do |nucleotide|
      translation_hash[nucleotide]
    end.join
  end

end
