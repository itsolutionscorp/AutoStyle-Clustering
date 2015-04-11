class Phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

end
