class Anagram
  def initialize(word)
    @word = word
    @sorted_word = sort(word)
  end

  def match(anagrams)
    anagrams
      .reject{ |a| a.downcase == @word.downcase }
      .select{ |a| sort(a) == @sorted_word }
  end

  private
    def sort(word)
      word.downcase.chars.sort.join
    end
end
