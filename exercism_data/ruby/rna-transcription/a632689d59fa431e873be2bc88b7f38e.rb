class Complement

  def self.dna_transcript
    {'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'}
  end

  def self.rna_transcript
    {'C' => 'G',
     'G' => 'C',
     'A' => 'T',
     'U' => 'A'}
  end

  def self.of_dna(string)
    convert_string(string, dna_transcript)
  end

  def self.of_rna(string)
    convert_string(string, rna_transcript)
  end

  def self.convert_string(string, transcript)
    strand = ""

    string.chars.each do |char|
      transcript.each do |key, value|
       strand << value if key == char
      end
    end

    strand
  end

end
