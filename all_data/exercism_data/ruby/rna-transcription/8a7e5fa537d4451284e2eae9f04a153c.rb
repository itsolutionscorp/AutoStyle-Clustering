class Complement

    DNA_COMPLEMENTS = { 'G' => 'C',
                        'C' => 'G',
                        'T' => 'A',
                        'A' => 'U' }
    RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

    def self.of_dna(aString)
        # #chars is shorthand for #each_char.to_a
        # This way here just handles the enumerator directly.
        # Prev: String -> Enumerator -> Array -> (map) -> Array
        # Now:  String -> Enumerator -> (map) -> Array
        aString.each_char.map{|c| DNA_COMPLEMENTS[c]}.join
    end

    def self.of_rna(aString)
        aString.each_char.map{|c| RNA_COMPLEMENTS[c]}.join
    end

end
