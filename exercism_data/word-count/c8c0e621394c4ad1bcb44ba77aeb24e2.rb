class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.downcase.scan(/[0-9a-z']+/)
  end

  def word_count
    words.each.with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
