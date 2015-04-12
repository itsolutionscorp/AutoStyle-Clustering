class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  def words
    @phrase.scan(/[\w\']+/)
  end
end
