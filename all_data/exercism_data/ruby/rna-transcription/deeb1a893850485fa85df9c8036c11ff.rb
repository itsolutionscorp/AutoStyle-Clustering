class Complement
	@@map = {"G" => "C",
  	       "C" => "G",
  	       "T" => "A",
  	       "A" => "U"}

  def self.of_dna(strand)
    strand.each_char.with_index do |letter, index| 
      raise ArgumentError if @@map[letter].nil?
      strand[index] = @@map[letter]
    end
    strand
  end


  def self.of_rna(strand)
  	strand.each_char.with_index do |letter, index| 
      raise ArgumentError if @@map.key(letter).nil?
      strand[index] = @@map.key(letter)
    end
    strand
  end

end
