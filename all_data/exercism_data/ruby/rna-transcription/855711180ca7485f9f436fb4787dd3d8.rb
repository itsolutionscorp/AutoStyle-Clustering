class Complement

  attr_reader :transcript

  def self.transcript
    {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  end

  def self.of_dna(string)
    if string.chars.any? { |letter| transcript.fetch(letter, nil) == nil }
      raise ArgumentError
    else
      string.chars.map { |letter| transcript[letter] }.join
    end
  end

  def self.of_rna(string)
    if string.chars.any? { |letter| transcript.invert.fetch(letter, nil) == nil }
      raise ArgumentError
    else
      string.chars.map { |letter| transcript.invert[letter] }.join
    end
  end

end
