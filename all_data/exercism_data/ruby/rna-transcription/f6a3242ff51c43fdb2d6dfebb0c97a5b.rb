class Complement

    DNA_COMPLEMENTS = { 'G' => 'C',
                        'C' => 'G',
                        'T' => 'A',
                        'A' => 'U' }
    RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

    def self.of_dna(aString)
        # #tr and #gsub are specifically created for
        # character substitutions in strings, as such,
        # they may be ideal for this problem.
        # I chose gsub over tr because it seems I'm able
        # to keep hashes around with gsub, which I like
        # as they reinforce the conceptual relationship
        # between the complements.
        aString.gsub(/[GCTA]/, DNA_COMPLEMENTS)
    end

    def self.of_rna(aString)
        aString.gsub(/[CGAU]/, RNA_COMPLEMENTS)
    end

end
