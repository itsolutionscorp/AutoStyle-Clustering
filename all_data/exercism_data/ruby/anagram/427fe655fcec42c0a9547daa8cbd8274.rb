class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
    @processed = process(word)
  end

  def match(candidates)
    candidates.each_with_object([]) do |c, a|
      next if c.downcase == word
      a << c if process(c) == @processed
    end
  end

  private

  def process(word)
    word.downcase.chars.sort.join
  end
end
