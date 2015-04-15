class Complement

  def self.of_dna(sequence)
    sequence.chars.map {|l| keys[l]}.join
  end

  def self.keys
    {
      "G"=>"C",
      "C"=>"G",
      "T"=>"A",
      "A"=>"U",
      "U"=>"A"
    }
  end

  def self.keys_rna
    {
      "G"=>"C",
      "C"=>"G",
      "T"=>"A",
      "A"=>"T",
      "U"=>"A"
    }

  end

  def self.of_rna(sequence)
    sequence.chars.map {|l| keys_rna[l]}.join
  end
end
