class Anagram
  def initialize(word)
    @word = word.downcase
    @my_letters = letter_occurrences(@word)
  end

  def match(words)
    words.select do |word|
      dc_word = word.downcase
      dc_word != @word && letter_occurrences(dc_word) == @my_letters
    end
  end

private
  def letter_occurrences(word)
    #                 ____________________________________
    #                 |             |                    |
    #         ________/_____________/_____________       |
    #         |       |             |            |       |
    #         ^       ^             v            v       v
    word.each_char.with_object(Hash.new(0)) do |letter, hash|
      hash[letter] += 1
    end
  end
end
