class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    counts = Hash.new(0)

    @phrase.scan(/\w+/).each_with_object(counts) do |word, counts|
      counts[word] += 1
    end
  end

end
