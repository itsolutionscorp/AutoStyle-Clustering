class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.scan(/\w+/)
    words.each_with_object(Hash.new(0)) {|word, freqs|
      freqs[word] += 1
    }
  end
end
