class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  # Returns a hash of unique words and the number of occurences in the sentence
  #
  # Example for "First: don't laugh. Then: don't cry.":
  #     {"first"=>1, "don't"=>2, "laugh"=>1, "then"=>1, "cry"=>1}
  def word_count
    words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end

  # Returns an array of words (alphanumerical and single quote)
  def words
    phrase.downcase.split(/[^'[[:alnum:]]]/).reject(&:empty?)
  end
end
