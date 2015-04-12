class Phrase
  attr_reader :a_phrase

  def initialize(a_phrase)
    @a_phrase = a_phrase
  end

  def word_count
    result = {}

    # Split the words into a hash
    words_hash = a_phrase.downcase.scan(/[a-zA-Z0-9]+/).group_by{ |elem| elem }

    # Push each word and its count into the result hash
    # Can be a one liner but would probably be too long to read
    words_hash.each { |key, val| result[key] = val.size }

    result
  end
end
