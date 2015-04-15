class Complement
    @@dna = ["G", "C", "T", "A"]
    @@rna = ["C", "G", "A", "U"]
    def self.of_dna(oldDNA)
        oldDNA = oldDNA.split("")
        newRNA = ""
        oldDNA.each { |nuc| newRNA<<@@rna[@@dna.index(nuc)] }
        return newRNA
    end
    def self.of_rna(oldDNA)
        oldRNA = oldDNA.split("")
        newDNA = ""
        oldRNA.each { |nuc| newDNA<<@@dna[@@rna.index(nuc)] }
        return newDNA
    end
end
