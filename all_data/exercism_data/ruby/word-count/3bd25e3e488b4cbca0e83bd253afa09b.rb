class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = {}
    @phrase.scan(/[\w']+/).each do |word|
      word.downcase!
      if word_count[word].nil?
        word_count[word] = 1
      else
        word_count[word] += 1
      end
    end
    word_count
  end
end
