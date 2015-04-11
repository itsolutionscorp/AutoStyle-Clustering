class Anagram
  def initialize base_word
    @base_word = base_word
  end

  def match list
    list.select {|word| is_anagram? word}
  end

  def is_anagram? word
    different_word?(word) && same_letters?(word)
  end

  private

  def different_word?(word)
    @base_word.downcase != word.downcase
  end

  def same_letters?(word)
    @base_word.downcase.chars.sort == word.downcase.chars.sort
  end
end
