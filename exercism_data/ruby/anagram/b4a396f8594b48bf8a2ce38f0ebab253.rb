class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    result = []
    words.each do |word|
      next if word.downcase == @word.downcase
      next if letter_count(word) != letter_count(@word)
      result << word
    end
    result
  end

  protected
  def letter_count(word)
    result = {}
    word.downcase.each_char do |c|
      result[c] ||= 0
      result[c] += 1
    end
    result
  end
end
