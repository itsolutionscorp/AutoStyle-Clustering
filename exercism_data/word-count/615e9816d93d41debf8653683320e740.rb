class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = get_words(@phrase)
    count_words(words)
  end

  def get_words(phrase)
    phrase.downcase.scan(/[\w']+/)
  end

  def count_words(words)
    count_of_words = Hash.new(0)
    words.each do |word|
      count_of_words[word] += 1
    end
    count_of_words
  end

end
