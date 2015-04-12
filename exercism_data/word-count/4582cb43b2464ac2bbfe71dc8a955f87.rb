class Phrase
  attr_accessor :phrase

  def initialize phrase
    self.phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new 0) { |word,hash| hash[word] += 1 }
  end

  def words
    phrase.downcase.scan /[\w']+/
  end
end
