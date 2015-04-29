class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    # memoize, but prevent external tampering
    @word_count ||= compute_word_count.freeze
  end

  private

  def compute_word_count
    # use 0 instead of nil for unknown keys
    result = Hash.new(0)

    words.each do |word|
      result[word] += 1
    end

    return result
  end

  NON_WORD = /[^\w]+/

  def words
    @words ||= @text.downcase.split(NON_WORD)
  end
end
