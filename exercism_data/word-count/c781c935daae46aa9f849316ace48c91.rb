class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def word_count
    counts = Hash.new 0

    tokenize.each { |token| counts[token] += 1 }

    counts
  end

  private

  def tokenize
    #\w+ matches one or more word characters
    @phrase.downcase.scan(/\w+/)
  end
end
