class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[normalize(word)] += 1
    end
  end

  private
  def normalize(word)
    word.downcase
  end

  def words
    @words ||= @string.scan(/\w+/)
  end
end
