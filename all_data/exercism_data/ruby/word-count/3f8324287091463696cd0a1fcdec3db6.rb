class Phrase
  WORD_SEPARATORS = / |,/

  attr_reader :words

  def initialize(phrase)
    @words = parse(phrase)
  end

  def word_count
    words.inject(Hash.new(0)) { |counts, word|
      counts[word] += 1
      counts
    }
  end

  private

  def parse(phrase)
    phrase.split(WORD_SEPARATORS).
      map {|word| word.gsub(/\W/, '').downcase }.
      reject { |word| word == '' }
  end
end
