class Complement
    def self.of_dna(strand)
        strand.tr('GCTA', 'CGAU')
    end

    def self.of_rna(strand)
        strand.tr('GCUA', 'CGAT')
    end
end
