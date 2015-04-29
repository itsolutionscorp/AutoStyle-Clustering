class Complement
  def self.transcript 
    {"G" => "C",
     "C" => "G",
     "T" => "A",
     "A" => "U"}
  end

  def self.of_dna(d_strand)
    r_strand = ""
    d_strand.each_char.with_index do |letter, index| 
      raise ArgumentError if self.transcript[letter].nil?
      r_strand[index] = self.transcript[letter]
    end
    r_strand
  end


  def self.of_rna(r_strand)
    d_strand = ""
  	r_strand.each_char.with_index do |letter, index| 
      raise ArgumentError if self.transcript.key(letter).nil?
      d_strand[index] = self.transcript.key(letter)
    end
    d_strand
  end

end
