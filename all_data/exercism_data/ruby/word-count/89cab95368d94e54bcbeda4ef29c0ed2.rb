class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_words
  end

  private
  def get_words
    @phrase.downcase.scan /[[:word:]]+/
  end

  def count_words
    counted ||= false
    unless counted
      words  ||= get_words
      counts ||= Hash.new(0)
      words.each {|word| counts[word] += 1 }
      counted = true
    end
    return counts # explicit return for clarity
  end

end
