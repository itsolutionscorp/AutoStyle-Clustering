class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  class << self
    def of_dna(strand)
      convert(strand, DNA_TO_RNA)
    end

    def of_rna(strand)
      convert(strand, RNA_TO_DNA)
    end

    def convert(strand, lookup_table)
      strand.split('').inject(''){|combined, current_value| combined + lookup_table[current_value]}
    end
  end
end
