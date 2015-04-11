class Anagram
  def initialize(word)
    @source = word.to_s.downcase
  end

  def match(string)
    get_words_from(string).each_with_object([]) do |word, result|
      result << word if is_anagram?(word)
    end
  end

private
  attr_reader :source

  def get_words_from(string)
    string.to_s.scan(/\w+/)
  end

  def is_anagram?(word)
    normalized_word = word.downcase
    source.chars.sort == normalized_word.chars.sort && source != normalized_word
  end
end
