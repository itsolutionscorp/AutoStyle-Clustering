class Complement

  TRANSCRIPTION = {"G" => "C", "C" => "G", "T" =>"A", "A"=>"U"}

  def self.of_dna(dna)
    dna.chars.map{|a| TRANSCRIPTION[a]}.join
  end

  def self.of_rna(dna)
    dna.chars.map{|a| TRANSCRIPTION.invert[a]}.join
  end
end
