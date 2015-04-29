class Phrase

  def initialize(phrase)
    # Since phrase is fixed, just generate the word count once.
    count_words(phrase)
  end

  def count_words(phrase)
    @counter = Hash.new(0)

    # Lowercase, strip punctuation (except for apostrophes),
    phrase = phrase.downcase.gsub(/[^\w']+/, ' ')

    # Split on whitespace and commas.
    words = phrase.split(/[,\s]/)

    # Count.
    words.each do |w|
      @counter[w] += 1
    end
  end

  def word_count
    @counter
  end

end
