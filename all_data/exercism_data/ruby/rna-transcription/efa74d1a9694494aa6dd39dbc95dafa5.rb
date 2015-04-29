class Complement

    DNA_COMPLEMENTS = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    def self.of_dna(dna)
        transcription(dna, DNA_COMPLEMENTS)
    end

    def self.of_rna(rna)
        transcription(rna, DNA_COMPLEMENTS.invert)
    end

    private

    def self.transcription(ns, cmap)
        ns.each_char.map {|x| cmap[x] }.join
    end
end
