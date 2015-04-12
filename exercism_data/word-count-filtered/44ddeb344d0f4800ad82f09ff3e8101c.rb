class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = Hash.new(0)
    @phrase.downcase.scan(/[\w']+/).each do |word|
      words[word] += 1
    end

    words
  end
end
