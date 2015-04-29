class Anagram
  def initialize word
    @word = word
  end

  def match list
    sortedWord = sort @word
    downcasedWord = @word.downcase
    list.select do |word|
      downcasedWord != word.downcase && sort(word) == sortedWord
    end
  end

  private

  def sort word
    word.downcase.chars.sort
  end
end
