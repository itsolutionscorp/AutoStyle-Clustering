class Complement
  class << self
    def of_dna(dna_strand)
      convert(dna_strand)
    end

    def of_rna(rna_strand)
      convert(rna_strand,ENTITIES.invert)
    end

    private
    ENTITIES={
     'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'
    }

    def convert(input,entities=ENTITIES)
      input.chars.map{|x| entities[x]}.join
    end

  end
end
