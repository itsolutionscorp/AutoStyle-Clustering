class Complement

    DNA = 'GCTA'
    RNA = 'CGAU'

    def self.of_dna(input)
        input.tr(DNA, RNA)
    end

    def self.of_rna(input)
        input.tr(RNA, DNA)
    end

end
