class Phrase
  attr_accessor :phrase

  def initialize phrase
    self.phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new 0) do |word,word_counts|
      word_counts[word] += 1
    end
  end

  def words
    phrase.downcase.scan /[\w']+/
  end
end
