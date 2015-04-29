class Anagram
  def initialize(string)
    @word = Word.new(string)
  end

  def match(anagram_candidates)
    anagram_candidates.select { |candidate| Word.new(candidate).anagram?(@word) }
  end
end

class Word
  attr_reader :string, :ordered_root

  def initialize(string)
    @string = string
    @ordered_root = string.downcase.scan(/\w/).sort
  end

  def anagram?(word)
    same_root?(word) && different_words?(word)
  end

  def different_words?(word)
    @string.downcase != word.string.downcase
  end

  def same_root?(word)
    word.ordered_root == self.ordered_root
  end
end
