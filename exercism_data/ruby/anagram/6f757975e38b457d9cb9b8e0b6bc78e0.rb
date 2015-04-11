class Anagram
  attr_reader :sorted_characters

  def initialize( word )
    @sorted_characters = sort_characters( word )
  end

  def match( possibilities )
    possibilities.select { |p| sort_characters(p) == sorted_characters }
  end

  private

  def sort_characters( word )
    word.downcase.each_char.sort
  end
end
