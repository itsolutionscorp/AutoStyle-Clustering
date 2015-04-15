class Words
  def initialize(words)
    @words = sanitize(words)
  end

  def count
    Hash[@words.collect { |word|
      [word, @words.count(word)]
    }]
  end

  private

  NON_WORD_CHARACTERS = /\W+/

  def sanitize(words)
    words.downcase.split(NON_WORD_CHARACTERS)
  end
end
