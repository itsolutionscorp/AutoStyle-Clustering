class Phrase
  WORD = /[\w|']+/

  def initialize(words)
    @normalized_words = words.downcase.scan(WORD)
  end

  def word_count
    @normalized_words.inject(Hash.new(0)) do |frequencies, word|
      frequencies[word] += 1
      frequencies
    end
  end
end
