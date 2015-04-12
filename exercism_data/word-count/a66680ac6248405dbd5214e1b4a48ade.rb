class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.downcase.split(/[^a-zA-Z0-9(')('')]+/)
  end

  def word_count
    words.uniq.each_with_object(Hash.new) do |word, hash|
      hash[word] = words.count(word)
    end
  end
end
