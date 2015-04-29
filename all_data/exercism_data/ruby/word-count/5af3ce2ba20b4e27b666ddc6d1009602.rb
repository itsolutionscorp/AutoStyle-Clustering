class Phrase
  def initialize str
    @phrase = str
  end

  def words
    @phrase.downcase.scan(/\w+/)
  end

  def word_count
    words_seen = Hash.new(0)
    words.each {|word|
      words_seen[word]+=1
    }
    words_seen
  end
end
