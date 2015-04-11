class Anagram
  attr_reader :body

  def initialize(body)
    @body = body 
  end

  def match(words)
    words.select{|w| anagram?(w)}
  end

  def anagram?(word)
    Comparison.new(word: word, anagram: body).result
  end
end

class Comparison

  def initialize(attrs={})
    @anagram = attrs[:anagram]
    @word = attrs[:word]
    build_comparison_arrays
  end

  def result
    @word_chars == @anagram_chars and @word.downcase != @anagram.downcase
  end

  private

  def build_comparison_arrays
    @word_chars = character_array(@word.downcase)
    @anagram_chars = character_array(@anagram.downcase)
  end

  def character_array(word)
    character_array = word.chars.map{|c| c}.sort
  end
end
