class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counter = Hash.new 0
    words.each.with_object(counter) { |word, counter| count word, counter }
  end

  private
  def words
    @phrase.scan /\w+/
  end

  def count word, counter
    counter[word.downcase] += 1 
  end
end
