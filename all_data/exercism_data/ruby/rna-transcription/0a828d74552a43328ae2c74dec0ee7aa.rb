class Complement
    @comps = ['GCTA', 'CGAU']
    
    def self.of_dna(str)
        return str.tr(@comps[0], @comps[1])
    end
    
    def self.of_rna(str)
        return str.tr(@comps[1], @comps[0])
    end
end
