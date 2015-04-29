class Complement
  TRANSCRIPTION = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(strand)
    rna = []
    strand.each_char { |nucleotide| rna << TRANSCRIPTION[nucleotide] }
    rna.join
  end

  def self.of_rna(strand)
    dna = []
    strand.each_char { |nucleotide| dna << TRANSCRIPTION.key(nucleotide)}
    dna.join
  end
end
