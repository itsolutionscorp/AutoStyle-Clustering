class Complement

  @@key = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'} 

  def self.of_dna input
    transcribe input, @@key
  end

  def self.of_rna input
    transcribe input, @@key.invert
  end

  def self.transcribe input, key
    input.chars.map { |letter| key[letter] }.join
  end

end
