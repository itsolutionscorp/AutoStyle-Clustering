class Complement

  COMPLEMENTS = {
    dna: {
      nucleotides: ["G", "C", "T", "A"],
      complements: :rna,
    },
    rna: {
      nucleotides: ["C", "G", "A", "U"],
      complements: :dna
    },
  }

  class << self
    COMPLEMENTS.each do |_na, complement|
      define_method("of_#{_na}") do |strand|
        before = complement.fetch(:nucleotides)
        after  = COMPLEMENTS.fetch(complement.fetch(:complements)).fetch(:nucleotides)
        strand.tr(for_conversion.join, conversions.join)
      end
    end
  end
end
