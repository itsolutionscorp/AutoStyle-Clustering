class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/[^a-zA-Z0-9_']/)
    count = {}

    words.each do |word|
      count[word.downcase] = 0
    end

    words.each do |word|
      count[word.downcase] +=1
    end
    count.delete("")
    count
  end
end
