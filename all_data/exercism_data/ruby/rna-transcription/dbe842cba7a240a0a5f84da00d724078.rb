class Complement
  def self.of_dna (input)
    key = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'} 
    transcribe(input, key)
  end

  def self.of_rna (input)
    key = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'} 
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
