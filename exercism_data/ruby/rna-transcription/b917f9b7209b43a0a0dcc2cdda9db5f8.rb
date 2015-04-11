class Complement  
     def self.of_dna (dna)     
       # Split strings into arrays of characters to be able to iterate through each character
       dna = dna.scan /\w/
        
       rna = ""
        dna.each_with_index {|val, index|  
          case val
          when "G"
            rna << "C"
          when "C"
            rna << "G"
          when "T"
            rna << "A"
          when "A"
            rna << "U"                        
          end       
        }
        rna
     end
     
     def self.of_rna (rna)     
       # Split strings into arrays of characters to be able to iterate through each character
       rna = rna.scan /\w/
        
       dna = ""
        rna.each_with_index {|val, index|  
          case val
          when "C"
            dna << "G"
          when "G"
            dna << "C"
          when "A"
            dna << "T"
          when "U"
            dna << "A"                        
          end       
        }
        dna
     end     
end
