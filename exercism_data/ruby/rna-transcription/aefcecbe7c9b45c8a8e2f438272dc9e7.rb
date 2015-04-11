class Complement
  TRANSCRIPTION = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| TRANSCRIPTION[nucleotide] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide| TRANSCRIPTION.invert[nucleotide] }.join
  end
end
