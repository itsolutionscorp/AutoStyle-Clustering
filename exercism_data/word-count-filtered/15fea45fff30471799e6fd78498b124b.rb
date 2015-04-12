class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count

    word = @phrase.split(" ")
    word_hash ={}
    word.each do |word|
      word_hash[word] = 1
    end

  end
end
