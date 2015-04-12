class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
    @words  = tidy_and_split(@phrase)
  end

  def word_count
    results = {}
    @words.each do |word|
      results[word] ? results[word] += 1 : results[word] = 1
    end
    results
  end

  def tidy_and_split(phrase)
    phrase.downcase.gsub(/[^0-9a-z ]/i, ' ').split(' ')
  end

end
