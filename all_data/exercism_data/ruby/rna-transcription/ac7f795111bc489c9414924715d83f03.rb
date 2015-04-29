module Complement
  class << self

    DNA_TO_RNA = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
    RNA_TO_DNA = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    def of_dna(strand)
      nucleotides(strand).map {|nt| nt = DNA_TO_RNA[nt] }.join
    end

    def of_rna(strand)
      nucleotides(strand).map {|nt| nt = RNA_TO_DNA[nt] }.join	
    end

  private

    def nucleotides(strand)
      strand.chars
    end

  end
end
