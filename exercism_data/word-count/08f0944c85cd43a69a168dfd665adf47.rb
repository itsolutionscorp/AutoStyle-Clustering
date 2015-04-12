class Phrase

  def initialize(string)
    @string = string.downcase
  end

  def word_count
    # Use default argument to Hash constructor to avoid excess logic in #reduce
    words.each_with_object(Hash.new(0)) do |word, word_list|
      word_list[word] += 1
    end
  end

  private

  def words
    @words ||= @string.scan(/\w+/)
  end
end
