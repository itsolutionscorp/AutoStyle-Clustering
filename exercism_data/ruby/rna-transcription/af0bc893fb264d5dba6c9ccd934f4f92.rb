class Complement
  class << self
    def of_dna(dna_strand)
      convert(dna_strand,:to_rna)
    end

    def of_rna(rna_strand)
      convert(rna_strand,:to_dna)
    end

    private
    ENTITIES={
     'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'
    }

    def convert(input,direction=:to_rna)
      entities=direction==:to_rna ? ENTITIES : ENTITIES.invert
      input.chars.map{|x| entities[x]}.join
    end

  end
end
