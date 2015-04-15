class Phrase
  WORD_SEPARATORS = / |,/
  INVALID_WORD_CHARS = '^[A-Za-z0-9]+'

  attr_reader :words

  def initialize(phrase)
    @words = parse(phrase)
  end

  def word_count
    words.inject({}) { |counts, word|
      counts[word] ||= 0
      counts[word] += 1
      counts
    }
  end

  private

  def parse(phrase)
    phrase.split(WORD_SEPARATORS).
      map {|word| word.tr(INVALID_WORD_CHARS, '').downcase }.
      reject { |word| word == '' }
  end
end
