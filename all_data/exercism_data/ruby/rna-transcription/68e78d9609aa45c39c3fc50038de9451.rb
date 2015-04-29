class Complement
  def self.of_dna(strand)
    complement = ""
    strand.each_char do |nucleotide|
      complement += get_rna_complement_of(nucleotide)
    end
    complement
  end

  def self.of_rna(strand)
    complement = ""
    strand.each_char do |nucleotide|
      complement += get_dna_complement_of(nucleotide)
    end
    complement
  end

  private

  def self.get_rna_complement_of(nucleotide)
    RNA_TRANSCRIPTOR[nucleotide.to_s]
  end

  def self.get_dna_complement_of(nucleotide)
    DNA_TRANSCRIPTOR[nucleotide.to_s]
  end

  RNA_TRANSCRIPTOR = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  DNA_TRANSCRIPTOR = {"G" => "C", "C" => "G", "T" => "A", "A" => "T", "U" => "A"}
end
