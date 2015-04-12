class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.scan(/['\w]+/).inject(Hash.new(0)) do |count, word|
      count[word.downcase] += 1
      count
    end
  end

end
