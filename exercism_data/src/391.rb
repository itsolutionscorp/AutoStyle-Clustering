class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = Hash.new 0
    @phrase.scan(/[\w']+/).each do |word|
      word.downcase!
      word_count[word] += 1
    end
    word_count
  end
end
