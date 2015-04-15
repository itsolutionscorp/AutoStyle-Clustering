class Complement

  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  RNA_TO_DNA = {
    "G" => "C",
    "C" => "G",    
    "U" => "A",
    "A" => "T",
  }

  def self.of_dna(strand)
    self.translate(DNA_TO_RNA, strand)
  end

  def self.of_rna(strand)
    self.translate(DNA_TO_RNA.invert, strand)
  end

  private
  def self.translate(translation_set, strand)
    strand.chars.collect do |nucleotide|
      translation_set[nucleotide]
    end.join    
  end

end
