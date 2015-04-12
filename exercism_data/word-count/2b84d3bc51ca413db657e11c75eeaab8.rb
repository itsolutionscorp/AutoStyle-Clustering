class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.scan(/\w+/)
    results = Hash.new(0) # for readability.

    words.each_with_object(results) do |word, results|
      results[word] += 1
    end
  end
end
