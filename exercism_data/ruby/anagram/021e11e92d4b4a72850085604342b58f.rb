class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    sorted_word = sort(@word)

    anagrams
      .reject{ |a| a.downcase == @word.downcase }
      .select{ |a| sort(a) == sorted_word }
  end

  private
    def sort(word)
      word.downcase.chars.sort.join
    end
end
