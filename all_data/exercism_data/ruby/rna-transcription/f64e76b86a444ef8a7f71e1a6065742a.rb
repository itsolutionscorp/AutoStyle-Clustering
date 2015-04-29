module Complement
  extend self

  def of_dna(strand)
    transcibe dna_to_rna, strand
  end

  def of_rna(strand)
    transcibe rna_to_dna, strand
  end

  private

  def transcibe(transcription, strand)
    strand.chars.map { |nucleotide| transcription[nucleotide] }.join
  end

  def dna_to_rna
    {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"
    }
  end

  def rna_to_dna
    dna_to_rna.invert
  end
end
