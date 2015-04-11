class Anagram

  def initialize(word)
    @target = word
  end

  def match(list)
    list.select{|word| anagram?(word)}
  end

  def anagram?(word)
    same_chars(word) && !same_word(word)
  end

  private

  def same_chars(word)
    sorted_chars(word) == sorted_chars(@target)
  end

  def same_word(word)
    @target.casecmp(word) == 0
  end

  def sorted_chars(word)
    word.downcase.chars.sort
  end
end
