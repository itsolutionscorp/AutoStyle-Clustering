class Complement
  class DNAComplement
    def self.name
      "dna"
    end

    def self.nucleotides
      ["G", "C", "T", "A"]
    end

    def self.complement
      RNAComplement
    end
  end

  class RNAComplement
    def self.name
      "rna"
    end

    def self.nucleotides
      ["C", "G", "A", "U"]
    end

    def self.complement
      DNAComplement
    end
  end

  COMPLEMENT_TYPES = [DNAComplement, RNAComplement]

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
