class Phrase
  def initialize(phrase)
    @phrase = phrase        
  end

  def word_count
    @count ||= count_words
  end

  private
  def count_words
    words.each_with_object(Hash.new(0)) {|word, counter|  counter[word] += 1 }
  end

  def words
    @phrase.downcase.scan(/\w+/)
  end

end
