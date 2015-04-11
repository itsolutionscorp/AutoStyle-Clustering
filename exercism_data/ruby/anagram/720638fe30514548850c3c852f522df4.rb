class Anagram
  def initialize(word)
    @word = Word.new word
  end

  def match(words)
    words.select { |given_word| word.anagram_of? given_word }
  end

  private

  attr_reader :word

  Word = Struct.new :word do
    def anagram_of?(other_word)
      other_letters = other_word.downcase.chars
      letters = word.downcase.chars

      letters != other_letters && letters.sort == other_letters.sort
    end
  end
end
