class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(hash_with_default) do |word, count|
      count[word] += 1
    end
  end

  private

  def words
    @phrase.downcase.split(/\W+/)
  end

  def hash_with_default
    Hash.new(0)
  end
end
