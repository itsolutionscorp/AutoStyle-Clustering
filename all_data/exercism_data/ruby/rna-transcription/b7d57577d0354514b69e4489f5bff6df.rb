class Complement
  #create a hash with the paired values for both methods to access
  @transcription ={
    G: "C",
    C: "G",
    T: "A",
    A: "U"
  }
  
  def self.of_dna(dna_nucleotide)
    #make an array of strings, then make an empty string to take the RNA transcription
    dna_array = dna_nucleotide.split""
    @rna_nucleotide = ""

    #iterate for however many letters were given, transcribe
    dna_array.each{|dna_letter|
      @transcription.each{|key, value| if dna_letter == key.to_s  
          @rna_nucleotide = @rna_nucleotide + value.to_s
          end
      }
    }
      return @rna_nucleotide   
  end
  #and then reverse:
  def self.of_rna (rna_nucleotide)
    rna_array = rna_nucleotide.split""
    @dna_nucleotide = ""
    
    rna_array.each{|rna_letter|
      @transcription.each{|key, value| if rna_letter == value.to_s
          @dna_nucleotide = @dna_nucleotide + key.to_s
      end
      }
    }
    return @dna_nucleotide
    
  end
end
