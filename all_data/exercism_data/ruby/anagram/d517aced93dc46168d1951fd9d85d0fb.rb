class Anagram
  # Original input word
  attr_reader :word

  # The word with its letters sorted
  attr_reader :sorted_characters

  def initialize( word )
    @word = word
    @sorted_characters = sort_characters( word )
  end

  def match( possibilities )
    possibilities.select { |p| sort_characters(p) == sorted_characters }
  end

  private

  def sort_characters( word )
    word.downcase.each_char.sort.join
  end
end
