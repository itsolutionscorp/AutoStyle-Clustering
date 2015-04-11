class Anagram
  def initialize(word)
    @word = word
  end

  def match(word_list)
    normalized_word = normalized(@word)
    word_list.select do |member|
      anagram?(normalized_word, normalized(member))
    end
  end

  private
  def anagram?(incumbent, contender)
    contender != incumbent && contender.chars.sort == incumbent.chars.sort
  end

  def normalized(word)
    word.downcase
  end
end
