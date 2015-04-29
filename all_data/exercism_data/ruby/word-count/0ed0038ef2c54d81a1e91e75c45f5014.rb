class Phrase
  def initialize str
    @phrase = str
  end

  def tokenize
    @phrase.downcase.scan(/\w+/)
  end

  def word_count
    words_seen = Hash.new(0)
    tokenize.each {|word|
      words_seen[word]+=1
    }
    words_seen
  end
end
