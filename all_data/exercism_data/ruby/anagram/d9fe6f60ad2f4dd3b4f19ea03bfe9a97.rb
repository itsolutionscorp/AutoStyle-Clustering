class Anagram
  # Original input word
  attr_reader :word

  # The word with its letters sorted
  attr_reader :gram

  def initialize( word )
    @word = word
    @gram = gramify( word )
  end

  def match( possibilities )
    possibilities.select { |p| gramify(p) == gram }
  end

  private

  def gramify( word )
    word.downcase.each_char.sort.join
  end
end
