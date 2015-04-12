class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    array = @phrase.downcase.split(/\W/)
    array.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1 unless word.empty?
    end

    # now we need to somehow get each word out of the phrase
    # Pseudo-code:
    #
    # for-each word in phrase:
    #   count word
    # return my-counts-of-words
    #

  end
end
