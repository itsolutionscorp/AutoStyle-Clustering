class Anagram
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(words)
    filtered_words(words).select do |word|
      clean(word) == clean(input)
    end
  end

  def clean(text)
    text.downcase.chars.sort.join("")
  end

  def filtered_words(text)
    text.reject do |word|
      word.downcase == input.downcase
    end
  end
end
