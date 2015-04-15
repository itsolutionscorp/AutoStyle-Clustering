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
        outString = ''
        aString.each_char do |c|
            outString += DNA_COMPLEMENTS[c]
        end
        outString
    end

    def self.of_rna(aString)
        outString = ''
        aString.each_char do |c|
            outString += RNA_COMPLEMENTS[c]
        end
        outString
    end

end
