class Complement
  # This method takes a dna strand and returns its rna complement
  def self.of_dna(dna_strand)
    rna = []
    dna = dna_strand.split("")
    #for each element in the dna array push the appropriate complement 
    #into the rna array.
    dna.each do |letter| 
      if letter == "G"
        rna << "C"
        elsif letter == "C"
        rna << "G"
        elsif letter == "T"
        rna << "A"
        elsif letter == "A"
        rna << "U" 
      end
    end
    #convert rna into a string so the test will pass.
    rna.join
  end
  # This method works the same way as the above method with the semantic 
  # difference being it takes in an rna strand then returns it's dna complement.
  def self.of_rna(rna_strand) 
    dna = []
    rna = rna_strand.split("")
    rna.each do |letter| 
      if letter == "C"
        dna << "G"
        elsif letter == "G"
        dna << "C"
        elsif letter == "A"
        dna << "T"
        elsif letter == "U"
        dna << "A" 
      end
    end
    dna.join
  end
end
