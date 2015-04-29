# Phrase represents a block of text and can return
# information about word counts.
# Design Notes: This class is designed so that you
# can pass in a different string after it has been
# initialized and still get back the correct count.
# This may prove to be an unecessary bit of complexity
# and it may end up being a better design descision
# to make this a "static" instance with no ability
# to set text to another value.  I'd love feedback.
class Phrase
  attr_accessor :text
  attr_reader :words_counted

  def initialize(text)
    @text = text || ''
    @words_counted = false
  end

  def word_count
    words_counted ? @word_count : @word_count = count_words
  end

  def text=(new_text)
    @text = text
    @words_counted = false
  end

  private

  # Loops through all of the words in the set
  # and increments the dictionary each time.
  # The dictionary is initialized to return 0 if
  # a word isn't found
  def count_words
    Hash.new(0).tap do |word_counts|
      words.each { |word| word_counts[word] += 1 }
      @words_counted = true
    end
  end

  def words
    text.downcase
        .gsub(special_character_regex, '')
        .split(word_delimiter_regex)
  end

  def special_character_regex
    /[:\.!&@\$%\^]/
  end

  def word_delimiter_regex
    /[ ,]+/
  end
end
