class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    get_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
    #counts
  end

  def get_words
    normalize.scan(/\w+/)
  end

  def normalize
    @phrase.downcase
  end
end
