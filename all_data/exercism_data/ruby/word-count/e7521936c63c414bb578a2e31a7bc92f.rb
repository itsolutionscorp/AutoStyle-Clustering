class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    WordCounter.new(words).count
  end

  def words
    @sentence.split(%r{\W}).collect(&:downcase)
  end

end

class WordCounter
  def initialize(words)
    @words = remove_empty(words)
  end

  def count
    result = Hash.new 0
    @words.each { |word| result[word] += 1 }
    result
  end

private
  def remove_empty list_of_words
    list_of_words.select { |w| !w.empty? }
  end
end
