require 'pry'

class Phrase
  def initialize sentence
    @sentence = sentence
    @words = @sentence.downcase.split(/[^\w']+/)
  end

  def word_count
    @words.each.inject({}) do |c, w|
      c[w] = c.fetch(w, 0) + 1
      c
    end
  end
end
