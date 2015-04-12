require_relative 'word_check'

class Phrase
  include WordCheck

  attr_reader :words

  def initialize(words)
    @words = scrubbed(words)
    @count = { }
    @counted = false
  end

  def scrubbed(words)
    not_compound = not_apostrophied(words)
    apostrophied(words) + normalized(not_compound)
  end

  def apostrophied(words)
    words.downcase.split.select { |word| word.include?("'") }
  end

  def not_apostrophied(words)
    words.downcase.split - apostrophied(words)
  end

  def normalized(words)
    cleaned = words.join(' ').gsub(/\W/, ' ')
    cleaned.downcase.split
  end

  def tabulate
    @words.each { |word| word_check(word) }
    @counted = true
    @count
  end

  def word_count
    @counted ? @count : tabulate
  end

end
