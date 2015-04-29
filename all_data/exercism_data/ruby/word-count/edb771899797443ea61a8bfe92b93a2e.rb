class Phrase
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def word_count
    count(words(@sentence.downcase))
  end

  def word_count_case_sensitive
    count(words(@sentence))
  end

  private

  def words(sentence)
    sentence.scan(/\w+/)
  end

  def count(words)
    words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end
end
