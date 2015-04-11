class Anagram
  def initialize base_word
    @base_word = base_word
    @sorted_base_letters = @base_word.downcase.chars.sort
  end

  def match list
    list.select {|candidate| is_anagram? candidate }
  end

  def is_anagram? candidate
    word = candidate.downcase
    @base_word != word && @sorted_base_letters == word.chars.sort
  end
end
