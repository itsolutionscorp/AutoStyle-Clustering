##
# Class that counts the words in a phrase.
class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_in_phrase.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def words_in_phrase
    phrase.downcase
          .split(/[^a-z0-9_']/)
          .select { |word| !word.empty? }
  end
end
