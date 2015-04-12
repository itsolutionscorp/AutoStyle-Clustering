class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = Hash.new {|h, k| h[k] = 0 }
    each_word {|word| counts[word] += 1 }
    return counts
  end

  def each_word
    @phrase.split(/\W+/).each do |word|
      yield word.downcase
    end
  end
end
