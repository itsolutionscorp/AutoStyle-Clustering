class Phrase

  def initialize phrase
    words = phrase.downcase.split(/[\W_]+/)
    @values = words.inject({}) do |h, word|
      h[word] ? h[word] += 1 : h[word] = 1
      h
    end
  end

  def word_count
    @values
  end

end
