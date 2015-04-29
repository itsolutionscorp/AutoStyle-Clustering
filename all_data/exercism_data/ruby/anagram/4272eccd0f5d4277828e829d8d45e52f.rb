class Anagram

  def initialize source_word
    @source_word = source_word
  end

  def match candidates
    candidates.select do |word| 
      WordPair.new(@source_word, word).anagram?
    end
  end

end

class WordPair

  def initialize(left, right)
    @left = left
    @right = right
  end

  def anagram? 
    contains_the_same_letters? and different_words?
  end

  def contains_the_same_letters? 
    word_signature(@left) == word_signature(@right)
  end

  def different_words? 
    @left.downcase != @right.downcase
  end

  def word_signature word
    word.downcase.split(//).sort.to_s
  end

end
