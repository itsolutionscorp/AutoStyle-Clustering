class Complement
  @matches = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(input)
    rna = input.split("").map do |letter|
      if @matches[letter] == nil
        raise(ArgumentError)
      else
        @matches[letter]
      end
    end
    rna.join
  end

  def self.of_rna(input)
    dna = input.split("").map do |letter|
      if @matches.key(letter) == nil
        raise(ArgumentError)
      else
        @matches.key(letter)
      end
    end
    dna.join
  end
end
