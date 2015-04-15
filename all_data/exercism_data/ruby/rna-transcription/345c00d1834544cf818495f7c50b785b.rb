module Complement
    @dna_complements={'A'=>'U', 'C'=>'G', 'G'=>'C', 'T'=>'A'}
    @rna_complements={'A'=>'T', 'C'=>'G', 'G'=>'C', 'U'=>'A'}

    def Complement.of_dna(s)
        s.chars.map { |c| @dna_complements[c] }.join
    end

    def Complement.of_rna(s)
        s.chars.map { |c| @rna_complements[c] }.join
    end
end
