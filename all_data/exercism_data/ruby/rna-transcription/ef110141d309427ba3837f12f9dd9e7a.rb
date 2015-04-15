class Complement
    DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    RNA_TO_DNA = DNA_TO_RNA.invert

    def self.of_dna(dna)
        transcribe(dna, DNA_TO_RNA)
    end

    def self.of_rna(rna)
        transcribe(rna, RNA_TO_DNA)
    end

    private
    def self.transcribe(strand, complements)
        strand.chars.map{|nucleotide| complements[nucleotide]}.join
    end
end
