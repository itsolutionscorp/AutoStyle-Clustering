class Anagram
  def initialize(word)
    @word = word
  end

  def match (word_list)
    word_list.select { |word| compare_anatomy(word) unless word.downcase == @word}
  end

  def disect_and_sort(word)
    word.downcase.chars.sort
  end

  def compare_anatomy(word)
      word if disect_and_sort(@word) == disect_and_sort(word)
  end
end
