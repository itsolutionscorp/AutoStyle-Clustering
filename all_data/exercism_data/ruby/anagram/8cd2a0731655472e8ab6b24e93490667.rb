class Anagram
  def initialize(base_word)
    @base_word = base_word.downcase
  end

  def match(list)
    list.select {|word| is_anagram?(word.downcase)}
  end

  private

  def is_anagram?(word)
    different_word?(word) && same_letters?(word)
  end

  def different_word?(word)
    @base_word != word
  end

  def same_letters?(word)
    base_letters == word.chars.sort
  end

  def base_letters
    @base_letters ||= @base_word.chars.sort
  end
end
