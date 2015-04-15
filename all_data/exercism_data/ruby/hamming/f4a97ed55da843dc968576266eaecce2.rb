class Hamming
  def self.compute(word_1, word_2)
    character_pairs = word_1.split(//).zip(word_2.split(//))

    character_pairs.inject(0) do |distance, chars|
      distance + distance_of_single_chars(*chars)
    end
  end

  private

  def self.distance_of_single_chars(char_1, char_2)
    char_1 == char_2 ? 0 : 1
  end
end
