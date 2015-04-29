# Given a DNA strand, returns its RNA complement (per RNA transcription)
class Complement
  def self.of_dna(dna_strand)
    translate_strand(
      dna_strand,
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    )
  end

  def self.of_rna(rna_strand)
    translate_strand(
      rna_strand,
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
    )
  end

  def self.translate_strand(strand, translations)
    result_strand = ''

    strand.each_char do |nucleotide|
      result_strand << translate_nucleotide(nucleotide, translations)
    end

    result_strand
  end

  def self.translate_nucleotide(nucleotide, translations)
    translations[nucleotide]
  end
end
