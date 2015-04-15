class Hamming
  def self.compute(string_1, string_2)

    zipped_strings = string_1.chars.zip(string_2.chars)

    zipped_strings.inject(0) do |hamming_distance , (character_1, character_2)|
      character_1 == character_2 ? hamming_distance : hamming_distance += 1
    end
  end
end
