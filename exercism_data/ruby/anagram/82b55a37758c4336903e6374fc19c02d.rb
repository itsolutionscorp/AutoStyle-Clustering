class Anagram
  def initialize(term)
    @term = term
  end

  def match(words)
    words.each_with_object([]) { |word, matches| 
      matches << word if anagram? word
    }
  end

  private

  def anagram?(word)
    word.chars.sort == @term.chars.sort
  end
end
