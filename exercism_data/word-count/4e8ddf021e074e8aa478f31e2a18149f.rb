class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def word_count
    normalized = @phrase.downcase
    words = split_words normalized

    count_words words
  end

  private

  def split_words phrase
    #\w+ matches one or more word characters
    phrase.scan(/\w+/)
  end

  def count_words words
    counts = Hash.new 0

    words.each { |w| counts[w] += 1 }

    counts
  end
end
