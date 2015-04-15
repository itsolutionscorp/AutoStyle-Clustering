class Complement

  def self.of_dna (dna)
    #Create empty array
    rna = []
    #Create mapping
    com = {"G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"}
    #Loop over every character and apply mapping
    dna.each_char do |c|
      puts com[c]
      rna << (com[c])
    end
    #Create string from array to return result
    rna.join()
  end

    def self.of_rna (rna)
    #Create empty array
    dna = []
    #Create mapping
    com = {"C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"}
    #Loop over every character and apply mapping
    rna.each_char do |c|
      puts com[c]
      dna << (com[c])
    end
    #Create string from array to return result
    dna.join()
  end

end
