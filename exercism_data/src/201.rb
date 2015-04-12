class Phrase

  def initialize phrase
    words = phrase.downcase.split(/[\W_]+/)
    @values = words.inject({}) do |h, word|
      if h[word] 
        h[word] += 1
      else
        h[word] = 1
      end
      h
    end
  end

  def word_count
    @values
  end

end
