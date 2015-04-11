class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, count| count[word] += 1}
  end

  private

  def words
    @words.downcase.split(/\W+/)
  end
end
