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
    # start all word counts at 0
    result = Hash.new(0)

    each_word do |word|
      result[word.downcase] += 1
    end

    return result
  end

  WORD = /\w+/

  def each_word(&block)
    @text.scan(WORD, &block)
  end
end
