# A phrase that can be analyzed for its {#word_count word count}.
class Phrase
  # @param string [String] the raw phrase.
  def initialize(string)
    @string = string
  end

  # The words in this phrase.  Words are normalized to all lowercase.
  #
  # @return [Array<String>] Words in the order they appear in this phrase..
  def words
    # Actively scan for word characters instead of splitting on space characters so that punctuation is ignored.
    unnormalized_words = string.scan(/\w+/)
    normalized_words = unnormalized_words.map(&:downcase)

    normalized_words
  end

  # Counts how often each word in this phrase occurs.
  #
  # @return [Hash{String => Integer}] Maps each word to how many times it occurs in this phrase.
  def word_count
    words.each_with_object(Hash.new(0)) { |word, count_by_word|
      count_by_word[word] += 1
    }
  end

  private

  # @!attribute [r] string
  #   The raw `String` composing this phrase.
  #
  #   @return [String]
  attr_reader :string
end
