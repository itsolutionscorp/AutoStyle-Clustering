class Anagram

  def initialize(word)
    @target = word
  end

  def match(list)
    list.each_with_object([]) do |word, matches|
      matches << word if anagram?(word)
    end
  end

  def anagram?(word)
    same_chars(word) && !same_word(word)
  end

  private

  def same_chars(word)
    anagramized(word) == anagramized(@target)
  end

  def same_word(word)
    @target.casecmp(word) == 0
  end

  def anagramized(word)
    word.downcase.chars.sort
  end
end
