class Complement
    DNA_TO_RNA = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    def self.of_dna(dna)
        self.map(dna, DNA_TO_RNA)
    end

    def self.of_rna(rna)
        self.map(rna, RNA_TO_DNA)
    end

    private

    def self.map(strand, map)
        mapped_strand = ''
        
        strand.each_char do |c|
            mapped_strand += map[c]
        end
        mapped_strand
    end
end
