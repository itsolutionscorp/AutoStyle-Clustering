require "ostruct"

class Complement

  dna_complement = OpenStruct.new(name: "dna", nucleotides: ["G", "C", "T", "A"])
  rna_complement = OpenStruct.new(name: "rna", nucleotides: ["C", "G", "A", "U"])
  dna_complement.complement = rna_complement
  rna_complement.complement = dna_complement

  COMPLEMENT_TYPES = [dna_complement, rna_complement]

  class << self
    COMPLEMENT_TYPES.each do |complement_type|
      define_method("of_#{complement_type.name}") do |strand|
        before = complement_type.nucleotides
        after  = complement_type.complement.nucleotides
        strand.tr(before.join, after.join)
      end
    end
  end
end
