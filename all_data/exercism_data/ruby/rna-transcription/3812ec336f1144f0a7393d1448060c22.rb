class Complement
    @@dna_to_rna = {
        'g' => 'c',
        'c' => 'g',
        't' => 'a',
        'a' => 'u'
    }

    def Complement.of_dna(dna)
        hash = @@dna_to_rna
        
        dna.chars.map { |s| hash[s.downcase].upcase }.join('')
    end

    def Complement.of_rna(rna)
        hash = @@dna_to_rna.invert

        rna.chars.map { |s| hash[s.downcase].upcase }.join('')
    end
end
