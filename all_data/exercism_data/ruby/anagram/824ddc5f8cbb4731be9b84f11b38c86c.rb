class Anagram
  def initialize( word )
    @wordDowncased = word.downcase
    @sortedWord = @wordDowncased.split("").sort
  end

  def match( arr )
    arr.select{|checkedWord| checkedWord.downcase.split("").sort == @sortedWord && checkedWord.downcase != @wordDowncased }
  end
end
