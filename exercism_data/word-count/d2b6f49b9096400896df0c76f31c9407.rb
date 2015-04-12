class Phrase
  def initialize text
    @text = text
  end

  def word_count
    @word_count ||= words.reduce(Hash.new 0) do |word_count,word|
      word_count[word] += 1
      word_count
    end
  end

  private

  def words
    @words ||= @text.scan(WORD_PATTERN).map &:downcase
  end

  WORD_PATTERN = %r{ [\p{Word}']+ }x
end
