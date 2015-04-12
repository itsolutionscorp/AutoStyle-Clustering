class Hamming
  def compute(word_1, word_2)
    character_pairs = word_1.chars.zip(word_2.chars)

    character_pairs.count do |char_1, char_2|
      char_1 != char_2
    end
  end
end
