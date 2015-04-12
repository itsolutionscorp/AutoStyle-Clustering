class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def word_count
    counts = Hash.new 0

    #\W+ split on all non-word characters
    @phrase.downcase.split(/\W+/).each { |word| counts[word] += 1 }

    counts
  end
end
