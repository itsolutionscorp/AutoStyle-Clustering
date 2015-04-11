class Complement
  @@key = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'} 

  def self.of_dna (input)
    key = @@key
    transcribe(input, key)
  end

  def self.of_rna (input)
    key = @@key.invert
    transcribe(input, key)
  end

  def self.transcribe (input, key)
    input = input.chars
    output = String.new

    # loop through the input and check it against our key hash, if we find a key,
    # append it's value to the output string
    input.each { |letter| output += key.values_at(letter).join }

    output

  end

end
