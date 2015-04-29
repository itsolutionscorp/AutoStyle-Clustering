class Complement

  attr_reader :transcript

  def self.transcript
    {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  end

  def self.of_dna(string)
    raise ArgumentError if string.chars.none? { |letter| transcript.fetch(letter, nil)}
    string.chars.map { |letter| transcript[letter] }.join
  end

  def self.of_rna(string)
    raise ArgumentError if string.chars.none? { |letter| transcript.invert.fetch(letter, nil)}
    string.chars.map { |letter| transcript.invert[letter] }.join
  end

end
