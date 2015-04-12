class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}
    phrase.downcase.scan(/[\w']+/).each do |word|
      count[word] = count[word].to_i + 1
    end
    count
  end
end
