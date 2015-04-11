class Phrase
  INVALID_CHARS = /[^a-zA-Z0-9' ]/
  DELIMITERS    = /[\s\n]+/
  SPACE         = " "

  attr_reader :string

  def initialize(string)
    @string = string
  end

  def word_count
    count = Hash.new(0)
    normalize(string).split(DELIMITERS).each { |w| count[w] += 1 }
    count
  end

  private

  def normalize(str)
    str.gsub(INVALID_CHARS, SPACE).downcase
  end
end
