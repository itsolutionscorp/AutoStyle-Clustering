class Complement
	@@map = {"G" => "C",
  	       "C" => "G",
  	       "T" => "A",
  	       "A" => "U"}

  def self.of_dna(strand)
    strand.split("").each_with_index do |letter, index| 
      raise ArgumentError if letter == "U"
      strand[index] = @@map[letter]
    end
    strand
  end


  def self.of_rna(strand)
  	strand.split("").each_with_index do |letter, index| 
      raise ArgumentError if letter == "T"
      strand[index] = @@map.key(letter)
    end
    strand
  end

end
