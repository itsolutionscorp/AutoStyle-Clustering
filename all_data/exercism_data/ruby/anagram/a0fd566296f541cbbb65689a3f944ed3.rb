class Anagram
  def initialize(word)
    @word = word
  end

  def match (word_list)
    result = []
    word_list.each do |word|
      unless word.downcase == @word
        result << compare_anatomy(word) if compare_anatomy(word) != nil
      end
    end
    result
  end

  def disect_and_sort(word)
    word.downcase.split('').sort
  end

  def compare_anatomy(word)
      word if disect_and_sort(@word) == disect_and_sort(word)
  end
end
