class Phrase
  def initialize text
    @text = text
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new 0) do |word,word_count|
      word_count[word] += 1
    end
  end

  private

  def words
    @words ||= @text.downcase.scan(WORD_PATTERN)
  end

  WORD_PATTERN = %r{ [\p{Word}']+ }x
end
