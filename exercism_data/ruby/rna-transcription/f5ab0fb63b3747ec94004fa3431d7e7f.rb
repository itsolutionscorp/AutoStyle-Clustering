class Complement

    #Complements
    # DNA    RNA
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`

    # Instead of using cases to lookup complements,
    # ruby's Hash feature seems more ideal.

    DNA_COMPLEMENTS = { 'G' => 'C',
                        'C' => 'G',
                        'T' => 'A',
                        'A' => 'U' }
    RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

    def self.of_dna(aString)
        # I saw the map method used in other's
        # Did a little research on it, seems like an elegant way
        # to solve the problem.
        aString.chars.map{|c| DNA_COMPLEMENTS[c]}.join
    end

    def self.of_rna(aString)
        aString.chars.map{|c| RNA_COMPLEMENTS[c]}.join
    end

end
