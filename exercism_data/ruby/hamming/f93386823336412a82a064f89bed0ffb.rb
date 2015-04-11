class Hamming < Struct.new(:first_chars, :second_chars)
  def self.compute(first_string, second_string)
    new(first_string.chars, second_string.chars).compute
  end

  def compute
    pair_characters.
    count do |first_character, second_character|
      first_character != second_character
    end
  end

  def pair_characters
    first_chars.zip(second_chars)
  end
end
