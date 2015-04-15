# Given a DNA strand, returns its RNA complement (per RNA transcription)
class Complement
  DNA_TRANSLATIONS =
  {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TRANSLATIONS = DNA_TRANSLATIONS.invert

  def self.of_dna(dna_strand)
    translate_strand(dna_strand, DNA_TRANSLATIONS)
  end

  def self.of_rna(rna_strand)
    translate_strand(rna_strand, RNA_TRANSLATIONS)
  end

  def self.translate_strand(strand, translations)
    strand.each_char.map { |n| translate_nucleotide(n, translations) }.join
  end

  def self.translate_nucleotide(nucleotide, translations)
    translations[nucleotide]
  end
end
