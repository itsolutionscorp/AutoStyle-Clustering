class Anagram

  def initialize(target)
    @target = target.downcase
  end

  def match(word_list)
    word_list.find_all { |word| anagram_of_target?(word.downcase) }
  end

  def anagram_of_target?(word)
    return false if word == @target
    word.split('').sort == @target.split('').sort
  end

end
