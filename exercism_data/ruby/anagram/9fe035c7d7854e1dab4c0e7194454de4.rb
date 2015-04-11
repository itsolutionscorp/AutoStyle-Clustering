class Anagram
  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(words)
    original = word.downcase.chars.sort

    words.each_with_object([]) do |w, result|
      result << w if (w.downcase.chars.sort == original) && word.downcase != w.downcase
    end
  end
end
