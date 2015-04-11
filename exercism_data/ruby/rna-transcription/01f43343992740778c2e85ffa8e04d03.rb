class Complement
    def self.of_dna(strand)
        raise ArgumentError if strand =~ /U/
        rna_version = ""
        (0..strand.length).each do |letter|
            case strand[letter]
                when "G" then rna_version << "C"
                when "C" then rna_version << "G"
                when "T" then rna_version << "A"
                when "A" then rna_version << "U"
            end
        end
        return rna_version
    end

    def self.of_rna(strand)
        raise ArgumentError if strand =~ /T/
        dna_version = ""
        (0..strand.length).each do |letter|
            case strand[letter]
                when "C" then dna_version << "G"
                when "G" then dna_version << "C"
                when "A" then dna_version << "T"
                when "U" then dna_version << "A"
            end
        end
        return dna_version
    end
end
