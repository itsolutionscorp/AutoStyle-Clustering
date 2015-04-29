class Complement

    #Complements
    # DNA       RNA
    # `G` -> `C`
    # `C` -> `G`
    # `T` -> `A`
    # `A` -> `U`

    # This code has a lot of repeating. In my next
    # iteration I will try to clean it up a bit
    # and avoid duplication of code.

    def self.of_dna(aString)
        outString = ''
        aString.each_char do |c|
            outString += self.getDnaComplement(c)
        end
        outString
    end

    def self.of_rna(aString)
        outString = ''
        aString.each_char do |c|
            outString += self.getRnaComplement(c)
        end
        outString
    end

    def self.getDnaComplement(aChar)
        case aChar
        when 'G'
            'C'
        when 'C'
            'G'
        when 'T'
            'A'
        when 'A'
            'U'
        end
    end

    def self.getRnaComplement(aChar)
        case aChar
        when 'C'
            'G'
        when 'G'
            'C'
        when 'A'
            'T'
        when 'U'
            'A'
        end
    end

end
