class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, word_count| word_count[normalize(word)] += 1 }
  end

  private
  def normalize(word)
    word.downcase
  end

  def words
    @string.scan(/\w+/)
  end
end
