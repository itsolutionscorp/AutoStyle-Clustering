class Complement
    RNA = ['C', 'G', 'A', 'U']
    DNA = ['G', 'C', 'T', 'A']

    RNA_TO_DNA = Hash[RNA.zip(DNA)]
    DNA_TO_RNA = Hash[DNA.zip(RNA)]

    def self.of_dna(dna)
        convert(DNA_TO_RNA, dna)
    end

    def self.of_rna(rna)
        convert(RNA_TO_DNA, rna)
    end

    private
    def self.convert(hash, strand)
        strand.chars.map do |n| 
            hash[n]
        end.reduce "" do |m, o| 
            m.insert(-1, o)
        end
    end
end
