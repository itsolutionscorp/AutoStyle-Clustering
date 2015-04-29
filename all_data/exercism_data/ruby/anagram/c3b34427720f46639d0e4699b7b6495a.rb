class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |compare_word|
      not same?(compare_word) and anagram?(compare_word)
    end
  end

  private

  def same?(compare_word)
    @word.upcase == compare_word.upcase
  end

  def anagram?(compare_word)
    @word.upcase.chars.sort == compare_word.upcase.chars.sort
  end

end
