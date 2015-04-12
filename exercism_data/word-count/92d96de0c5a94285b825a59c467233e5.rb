# Counts the occurrences of each word in given phrase
class Phrase
  # @param [#to_s]
  def initialize(text)
    @text = text.to_s
  end

  # @return [Hash{String => Fixnum}] statistics for word occurrencies
  def word_count
    Hash.new { |hash, word| hash[word] = 0 }.tap do |hash|
      words.each { |word| hash[word.downcase] += 1 }
    end
  end

  # @return [<String>] array of words extracted from given phrase
  def words
    @text.split(/[^\w\d']+/i)
  end
end
