# Given a DNA strand, returns its RNA complement (per RNA transcription)
class Complement
  def self.of_dna(dna_strand)
    translations =
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    translate_strand(dna_strand, translations)
  end

  def self.of_rna(rna_strand)
    translations =
    {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
    }

    translate_strand(rna_strand, translations)
  end

  def self.translate_strand(strand, translations)
    result_strand = ''

    strand.chars.each do |nucleotide|
      result_strand << translate_nucleotide(nucleotide, translations)
    end

    result_strand
  end

  def self.translate_nucleotide(nucleotide, translations)
    translations[nucleotide]
  end
end
