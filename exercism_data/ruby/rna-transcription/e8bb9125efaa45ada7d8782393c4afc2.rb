class Complement
  @complements = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'}

  def self.of_dna(code)
    transcribe_code(code, @complements)
  end

  def self.of_rna(code)
    transcribe_code(code, @complements.invert)
  end

  def self.transcribe_code(code, complements)
    code.chars.map do |value|
      complements[value]
    end.join
  end
end
