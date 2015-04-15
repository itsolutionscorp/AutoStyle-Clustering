class Complement
  DNA_TO_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self
    def of_dna(dna_strand)
      convert_dna_to_rna_logic = -> (dna_nucleotide) {
        DNA_TO_RNA_MAP[dna_nucleotide]
      }
      dna_rna_converter(convert_dna_to_rna_logic, dna_strand)
    end

    def of_rna(rna_strand)
      convert_rna_to_dna_logic = -> (rna_nucleotide) {
        DNA_TO_RNA_MAP.key(rna_nucleotide)
      }
      dna_rna_converter(convert_rna_to_dna_logic, rna_strand)
    end

    def dna_rna_converter(logic, strand)
      strand.chars.map(&logic).join
    end
  end
end
