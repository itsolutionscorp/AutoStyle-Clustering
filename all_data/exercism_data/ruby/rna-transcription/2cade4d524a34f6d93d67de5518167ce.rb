module Complement

  DNA_COMPLEMENTS = {
    'G'.ord => 'C'.ord,
    'C'.ord => 'G'.ord,
    'T'.ord => 'A'.ord,
    'A'.ord => 'U'.ord
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  class << self
    [:dna, :rna].each do |nucleotide|
      define_method "of_#{nucleotide}" do |nucleotides|
        complement(nucleotides, const_get("#{nucleotide.upcase}_COMPLEMENTS"))
      end
    end
  end

  def self.complement(nucleotides, complements)
    nucleotides.each_byte.map { |b| complements[b].chr }.join
  end
end
