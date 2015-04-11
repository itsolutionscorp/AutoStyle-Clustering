class Complement
    DNA_TO_RNA = {
        'C' => 'G', 
        'G' => 'C',
        'T' => 'A',
        'A' => 'U'
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    def self.of_dna(dna)
        dna.tr(DNA_TO_RNA.keys.join, DNA_TO_RNA.values.join)
    end

    def self.of_rna(rna)
        rna.tr(RNA_TO_DNA.keys.join, RNA_TO_DNA.values.join)
    end

end
