class Anagram
  def initialize(word)
    @original_word = word
    @word = @original_word.downcase
  end

  def match(list)
    list.each_with_object([]) do |w, l|
      l << w if anagram?(w)
    end
  end

  private
  def anagram?(word)
    word = word.downcase
    different(word) && same_length(word) && same_characters(word)
  end

  def different(word)
    @word != word
  end

  def same_length(word)
    @word.length == word.length
  end

  def same_characters(word)
    @word.split('').sort == word.split('').sort
  end
end
