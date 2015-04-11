COMPHASH = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

class Complement
  def self.of_dna(s)
    "".tap {|r| s.each_char {|c| r << COMPHASH[c]}}
  end

  def self.of_rna(s)
    "".tap {|r| s.each_char {|c| r << COMPHASH.key(c)}}
  end
end
