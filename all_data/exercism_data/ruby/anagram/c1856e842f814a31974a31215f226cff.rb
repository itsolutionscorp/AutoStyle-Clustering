class Anagram

  def initialize(target)
    @target = target.downcase
  end

  def match(word_list)
    word_list.find_all { |word| anagram_of_target?(word.downcase) }
  end

  private

  def anagram_of_target?(word)
    return false if identical_to_target?(word)
    sort_letters(word) == sort_letters(@target)
  end

  def identical_to_target?(word)
    word == @target
  end

  def sort_letters(word)
    word.chars.sort
  end

end
