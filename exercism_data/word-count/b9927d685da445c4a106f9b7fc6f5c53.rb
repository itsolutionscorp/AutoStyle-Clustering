class Phrase

  def initialize(text)
    @text = text
  end

  def word_count
    words_without_punctuation.inject(Hash.new(0)) do |results, word|
      results[word] += 1
      results
    end
  end

  def words_without_punctuation
    @text.downcase.gsub(/[^\w\s]/, ' ').split
  end

end
