class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.lstrip # need to remove leading whitespace 
  end

  def word_count
    counts = Hash.new(0)

    @phrase.split(/\W+/).each do |word|
      counts[word] += 1
    end

    return counts
  end

end
