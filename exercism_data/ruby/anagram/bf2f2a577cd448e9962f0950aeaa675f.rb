class Anagram
  def initialize(word)
    @word = word
  end

  def match (word_list)
    word_list.select { |word| compare_anatomy(word) unless word.downcase == @word}
  end

  def dissect_and_sort(word)
    word.downcase.chars.sort
  end

  def compare_anatomy(word)
    dissect_and_sort(@word) == dissect_and_sort(word)
  end
end
