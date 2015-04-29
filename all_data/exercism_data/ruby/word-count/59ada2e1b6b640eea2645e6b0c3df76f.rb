class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/ /)

    words.inject({}) do |count, word|
      count[word] ? count[word] += 1 : count[word] = 1

      count
    end
  end
end
