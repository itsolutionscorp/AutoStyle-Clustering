class Phrase
  attr_reader :phrase
  def initialize(new_phrase)
    @phrase = new_phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, tally|
      tally[word] += 1
    }
  end

  private

  def words
    extract_words(phrase)
  end

  def extract_words(phrase)
    normalise(phrase).split
  end

  def normalise(phrase)
    phrase.gsub(/[^[:alnum:] ]/, '').downcase
  end
end
