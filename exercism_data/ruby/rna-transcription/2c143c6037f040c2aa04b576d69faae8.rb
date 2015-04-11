class ComplementTest < Minitest::Test
  module Complement
    def self.of_dna(dna_string)
      if dna_string.include?('U')
        raise ArgumentError, 'A DNA string cannot include uracil.'
      else
        dna_string.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
      end
    end

    def self.of_rna(rna_string)
      if rna_string.include?('T')
        raise ArgumentError, 'An RNA string can not include thymine.'
      else
        rna_string.gsub(/[GCAU]/, 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T')
      end
    end
  end
end
