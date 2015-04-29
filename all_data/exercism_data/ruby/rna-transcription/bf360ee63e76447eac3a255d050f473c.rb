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
    strand.chars.collect do |nucleotide|
      translation = translation_set[nucleotide]
      raise ArgumentError if translation.nil?
      translation
    end.join
  end

end
