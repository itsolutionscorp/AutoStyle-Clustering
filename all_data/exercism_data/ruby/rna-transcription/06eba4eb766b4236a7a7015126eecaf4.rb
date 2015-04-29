class Complement

  def self.of_dna(d_strand)
    r_strand = ""
    d_strand.each_char.with_index do |letter, index| 
      raise ArgumentError if transcript[letter].nil?
      r_strand << transcript[letter]
    end
    r_strand
  end


  def self.of_rna(r_strand)
    d_strand = ""
  	r_strand.each_char.with_index do |letter, index| 
      raise ArgumentError if transcript.key(letter).nil?
      d_strand << transcript.key(letter)
    end
    d_strand
  end

  private

  def self.transcript 
    {"G" => "C",
     "C" => "G",
     "T" => "A",
     "A" => "U"}
  end

end
