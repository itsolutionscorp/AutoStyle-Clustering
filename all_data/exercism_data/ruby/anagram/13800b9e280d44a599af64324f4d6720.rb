class Anagram

  def initialize word
    @sorted_word = sort word
  end

  def match list
    list.select { |word| anagram? word }
  end

  private

  def anagram? compare_word
    @sorted_word == sort(compare_word)
  end

  def sort word
    word.downcase.chars.sort.join
  end
end
