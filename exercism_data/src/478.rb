class Phrase

  def initialize phrase
    @words = phrase.downcase.scan(/\w+/)
  end

  def word_count
    results = Hash.new(0)

    @words.each_with_object(results) do |word, results|
      results[word] += 1
    end
  end
end
