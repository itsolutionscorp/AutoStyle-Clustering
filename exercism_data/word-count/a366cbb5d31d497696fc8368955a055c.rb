class Phrase
  UNECESSARY_CHARACTERS = /[^0-9a-z ,]/
  DELIMITERS = /[ ,]+/

  def initialize(input)
    @input = input.downcase
  end

  def word_count
    counts = Hash.new(0)

    find_words(@input).each do |word|
      counts[word] += 1
    end

    counts
  end

  private
  def find_words(input)
    input.gsub!(UNECESSARY_CHARACTERS, '')
    input.split(DELIMITERS)
  end
end
