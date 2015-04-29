class Complement
  def self.of_dna(string)
    transcription_hash = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    output = ""
    string.each_char do |char|
      output += transcription_hash[char]
    end
    output
  end

  def self.of_rna(string)
    transcription_hash = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}
    output = ""
    string.each_char do |char|
      output += transcription_hash[char]
    end
    output
  end
end
