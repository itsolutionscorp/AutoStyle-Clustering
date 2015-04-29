require "unicode_utils/downcase"

class Anagram

  attr_reader :word, :original

  def initialize(word)
    @original = word
    @word = normalize(word)
  end

  def match(words)
    words.select do |input|
      UnicodeUtils.downcase(original) != UnicodeUtils.downcase(input) &&
      word == normalize(input)
    end
  end

  def normalize(word)
    UnicodeUtils.downcase(word).chars.sort.join
  end

end
