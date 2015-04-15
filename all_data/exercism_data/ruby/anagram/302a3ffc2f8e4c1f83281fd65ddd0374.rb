class Anagram
  def initialize word
    @word = Word.new(word)
  end

  def match candidates
    candidates.each_with_object([]) do |candidate, matches|
      matches << candidate if @word.anagram? Word.new(candidate)
    end
  end
end

class Word
  attr_reader :term

  def initialize term
    @term = term.downcase
  end

  def letter_counts
    @term.split("").each_with_object(Hash.new(0)) do |letter, counts|  
      counts[letter] += 1
    end
  end

  def anagram? other_word
    unless self.eql? other_word
      self.letter_counts == other_word.letter_counts 
    end
  end

  def eql? other_word
    self.term == other_word.term
  end
end
