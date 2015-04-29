class Phrase
  def initialize str
    @phrase = str
  end

  def word_count
    counts = {}
    @phrase.downcase.split(/[!&@$%^-_,;:\.\s]+/).each do |word|
      if counts[word]
        counts[word] += 1
      else
        counts[word] = 1
      end
    end
    counts
  end
end
