class Complement

  def self.dna_transcript
    {'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'}
  end

  def self.of_dna(string)
    convert_string(string)
  end

  def self.of_rna(string)
    convert_string(string, dna_transcript.invert)
  end

  def self.convert_string(string, transcript = dna_transcript)
    string.chars.map do |letter|
      transcript[letter]
    end.join
  end

end
