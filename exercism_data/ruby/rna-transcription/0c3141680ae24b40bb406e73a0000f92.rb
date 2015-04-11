class Complement
    def self.of_dna(dna)
        dna.gsub('G','c').gsub('C','g').gsub('T','a').gsub('A','u').upcase
    end

    def self.of_rna(rna)
        rna.gsub('G','c').gsub('C','g').gsub('A','t').gsub('U','a').upcase
    end
end
