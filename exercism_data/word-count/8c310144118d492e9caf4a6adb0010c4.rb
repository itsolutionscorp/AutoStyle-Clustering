class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words(@phrase))
  end
  
  private
  def words(phrase)
    phrase.downcase.split(/\W/).reject(&:empty?)
  end
  
  def count(words)
    words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end
end
