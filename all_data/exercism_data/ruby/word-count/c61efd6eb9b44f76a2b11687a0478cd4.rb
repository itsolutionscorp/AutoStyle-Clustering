class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.downcase.split(/[^\w']+/)
  end

  def word_count
    words.each_with_object({}) { |word, total| total[word] ? total[word] += 1 : total[word] = 1 }
  end

end
