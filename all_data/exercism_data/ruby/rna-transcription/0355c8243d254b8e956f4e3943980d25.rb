class Complement

    DNA_COMPLEMENTS = { 'G' => 'C',
                        'C' => 'G',
                        'T' => 'A',
                        'A' => 'U' }
    RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
    # #join on an array is more correct.
    # #to_s is an alias of #inspect
    # which returns a String like "[\"a\", \"b\", \"c\"]"
    DNA = DNA_COMPLEMENTS.keys.join
    RNA = RNA_COMPLEMENTS.keys.join

    def self.of_dna(aString)
        aString.gsub(/[#{DNA}]/, DNA_COMPLEMENTS)
    end

    def self.of_rna(aString)
        aString.gsub(/[#{RNA}]/, RNA_COMPLEMENTS)
    end

end
