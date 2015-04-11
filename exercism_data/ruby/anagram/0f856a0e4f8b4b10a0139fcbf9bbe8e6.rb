class Anagram
  def initialize(word)
    @word = Word.new(word.downcase)
  end

  def match(words)
    words = words.map { |word| Word.new(word.downcase) }
    words.each_with_object([]) do |word, matches|
      matches << word if word.is_anagram_of?(@word)
    end
  end
end

class Word < String
  def is_anagram_of?(word)
    self != word && sort_chars == word.sort_chars
  end

  def sort_chars
    chars.sort.join
  end
end
