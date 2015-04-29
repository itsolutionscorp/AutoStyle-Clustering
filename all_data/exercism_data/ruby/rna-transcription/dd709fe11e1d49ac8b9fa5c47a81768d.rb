class Complement

  @dna_to_rna = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  @rna_to_dna = Hash[@dna_to_rna.to_a.collect(&:reverse)]

  def self.of_dna(strand)
    translate strand, @dna_to_rna
  end

  def self.of_rna(strand)
    translate strand, @rna_to_dna
  end

  private

  def self.translate(strand, translation_hash)
    strand.split(//).map do |nucleotide|
      translation_hash[nucleotide]
    end.join
  end

end
