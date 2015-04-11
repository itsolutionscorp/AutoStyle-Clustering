class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.downcase.split(/[^\w']+/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, total| total[word] += 1 }
  end

end
