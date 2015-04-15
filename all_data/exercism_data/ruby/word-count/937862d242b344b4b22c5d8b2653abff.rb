class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object({}) do |word, hash|
      hash[word] = hash.has_key?(word) ? hash[word] + 1 : 1
    end
  end

  private
  def words
    @phrase.downcase.gsub(/[^a-z0-9]/, ' ').split
  end
end
