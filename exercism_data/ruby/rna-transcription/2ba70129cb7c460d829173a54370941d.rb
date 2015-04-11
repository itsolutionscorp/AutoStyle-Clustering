class Complement

  def self.transcription_of_dna
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.transcription_of_rna
    transcription_of_dna.invert
  end


  def self.of_rna(rna)
    transcribed_rna = String.new
    rna.each_char do |char|
      transcribed_rna += transcription_of_rna[char]
    end
    transcribed_rna
  end

  def self.of_dna(dna)
    transcribed_dna = String.new
    dna.each_char do |char|
      transcribed_dna += transcription_of_dna[char]
    end
    transcribed_dna
  end
end
