class Anagram
  def initialize(source)
    @source = source
  end

  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  private

  attr_reader :source

  def anagram?(word)
    uses_same_chars?(word) && !same_word?(word)
  end

  def uses_same_chars?(word)
    sorted_chars(source) == sorted_chars(word)
  end

  def sorted_chars(word)
    word.downcase.chars.sort.join
  end

  def same_word?(word)
    source.downcase == word.downcase
  end
end
