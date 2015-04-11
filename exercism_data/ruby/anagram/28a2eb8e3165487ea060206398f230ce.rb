class Anagram
  def initialize word
    @word = Word.new(word)
  end

  def match candidates
    candidates.find_all do |candidate|
      @word.anagram? Word.new(candidate)
    end
  end
end

class Word
  attr_reader :term

  def initialize term
    @term = term.downcase
  end

  def canonical_form
    @term.chars.sort
  end

  def anagram? other_word
    unless self.eql? other_word
      self.canonical_form == other_word.canonical_form
    end
  end

  def eql? other_word
    self.term == other_word.term
  end
end
