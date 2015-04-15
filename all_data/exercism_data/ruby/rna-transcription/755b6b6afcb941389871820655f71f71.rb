class Complement

    def self.of_dna(strand_input)
      strand_in(strand_input, "RNA")
    end

    def self.of_rna(strand_input)
      strand_in(strand_input, "DNA")
    end

    def self.strand_in(strand, conv_to)
        conv_strand = ""
        s1 = strand.split('')

        s1.each do |i|
          conv_strand << create_complement(i, conv_to)
        end 
       
        conv_strand
    end
    

    def self.create_complement(nucleotide, to)

        if nucleotide == 'A'
            if to == "RNA"
                'U'
            else
                'T'
            end

        elsif nucleotide == 'C' 
            'G'
        
        elsif nucleotide == 'G' 
            'C'
            
        elsif nucleotide == 'T' 
            if to == "RNA"
                'A'
            else
                "invalid input"
            end

        elsif nucleotide == 'U' 
            if to == "DNA"
                'A'
            else
                "invalid input"
            end

        else
            "invalid input"
        end
    end
end



=begin
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
=end
