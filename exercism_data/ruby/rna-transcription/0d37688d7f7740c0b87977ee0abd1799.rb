class Complement 
    DNA_SEQ = "GCTA"
    RNA_SEQ = "CGAU"

    def self.of_dna(str)
        str.tr(DNA_SEQ, RNA_SEQ)
    end

    def self.of_rna(str)
        str.tr(RNA_SEQ, DNA_SEQ)
    end
end        
