class Complement

    DNA_COMPLEMENTS = { 'G' => 'C',
                        'C' => 'G',
                        'T' => 'A',
                        'A' => 'U' }
    RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
    DNA = DNA_COMPLEMENTS.keys.to_s
    RNA = RNA_COMPLEMENTS.keys.to_s

    def self.of_dna(aString)
        # I figured out how to use variables in regex's.
        # This way might be more expensive, but I didn't
        # like the nucleotides being hard-coded in there.
        aString.gsub(/[#{DNA}]/, DNA_COMPLEMENTS)
    end

    def self.of_rna(aString)
        aString.gsub(/[#{RNA}]/, RNA_COMPLEMENTS)
    end

end
