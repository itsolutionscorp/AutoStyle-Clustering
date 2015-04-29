class Anagram
  def initialize(word)
    @source = word.to_s
  end

  def match(array)
    array.select do |word|
      is_anagram? word
    end
  end

private
  attr_reader :source

  def is_anagram?(word)
    normalized_source = source.downcase
    normalized_word   = word.downcase
    normalized_source.chars.sort == normalized_word.chars.sort &&
      normalized_source != normalized_word
  end
end
