class Anagram
  def initialize(source)
    @source = source.downcase
  end

  def match(words)
    words.each_with_object([]) do |word, result|
      result << word if anagram?(word)
    end
  end

private
  attr_reader :source

  def anagram?(word)
    different?(word) && same_chars?(word)
  end

  def different?(word)
    source != word.downcase
  end

  def same_chars?(word)
    word.downcase.chars.sort == source.chars.sort
  end

end
