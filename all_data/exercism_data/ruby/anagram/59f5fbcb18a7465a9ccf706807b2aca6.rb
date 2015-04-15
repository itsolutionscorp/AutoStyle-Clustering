class Anagram
  def initialize word
    @word = word
  end

  def match list
    sortedWord = sort @word
    downcasedWord = @word.downcase
    list.inject([]) do |list, word|
      list << word if downcasedWord != word.downcase && sort(word) == sortedWord && !list.include?(word)
      list
    end
  end

  private

  def sort word
    word.downcase.split('').sort.join('')
  end
end
