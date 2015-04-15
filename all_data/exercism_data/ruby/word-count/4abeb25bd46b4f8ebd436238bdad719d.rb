class Phrase
  WORD_PATTERN = /[A-Za-z0-9]+/

  def initialize(text)
    @text = text
  end

  def word_count
    return @word_count if defined?(@word_count)

    @word_count = Hash.new(0)

    @text.scan(WORD_PATTERN) do |word|
      @word_count[word.downcase] += 1
    end

    @word_count
  end
end
