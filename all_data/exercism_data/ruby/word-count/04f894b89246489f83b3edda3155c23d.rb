class Phrase
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    get_words.each_with_object( Hash.new(0) ){|word, counts|
      counts[word] += 1
    }
  end

  def get_words
    phrase.downcase.scan(/\w+/)
  end
end
