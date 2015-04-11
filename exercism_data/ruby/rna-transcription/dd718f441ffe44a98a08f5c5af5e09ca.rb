class Complement

  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    self.translate(DNA_TO_RNA, strand)
  end

  def self.of_rna(strand)
    self.translate(RNA_TO_DNA, strand)
  end

  private
  def self.translate(translation_set, strand)
    translation = strand.chars.map! do |nucleotide|
      translation_set[nucleotide]
    end.join

    raise ArgumentError if translation.size != strand.size

    translation
  end

end
