class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/\w+/).inject(Hash.new(0)) do |counter, word|
      counter[word] += 1 and counter
    end
  end

end
