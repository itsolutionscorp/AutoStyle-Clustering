class Phrase

  attr_reader :phrase, :words

  def initialize(phrase)
    @phrase = phrase
    @words  = tidy_and_split_phrase
  end

  def word_count
    words.each_with_object({}) do |word, hash|
      hash[word] ? hash[word] += 1 : hash[word] = 1
    end
  end

  def tidy_and_split_phrase
    phrase.downcase.scan(/\w+/i)
  end

end
