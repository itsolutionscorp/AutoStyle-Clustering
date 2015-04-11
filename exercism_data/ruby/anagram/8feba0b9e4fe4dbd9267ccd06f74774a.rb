class Anagram
  def initialize( word )
    @word_downcased = word.downcase
    @sorted_word = @word_downcased.split("").sort
  end

  def match( arr )
    arr.select{|checked_word| anagram?( checked_word ) }
  end

  private

  def anagram?( anagram )
    anagram = anagram.downcase
    anagram.split("").sort == @sorted_word && anagram != @word_downcased
  end
end
