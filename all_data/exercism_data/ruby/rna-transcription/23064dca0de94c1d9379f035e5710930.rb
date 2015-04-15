class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  class << self
    def of_dna(strand)
      strand.split('').inject(''){|combined, current_value| combined + DNA_TO_RNA[current_value]}
    end

    def of_rna(strand)
      strand.split('').inject(''){|combined, current_value| combined + RNA_TO_DNA[current_value]}
    end
  end
end
