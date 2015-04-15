class Complement

  @complements = { 
                  "rna" => { "G" => "C", "C" => "G", "T" => "A", "A" => "U" },
                  "dna" => { "C" => "G", "G" => "C", "A" => "T", "U" => "A" } 
                }

  def self.of_dna(sequence)
    new = ""
    sequence.each_char do |letter|
      new += @complements["rna"][letter]
    end 

    new
  end

  def self.of_rna(sequence)
    new = ""
    sequence.each_char do |letter|
      new += @complements["dna"][letter]
    end 

    new
  end
end
