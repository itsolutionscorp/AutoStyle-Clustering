class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = @phrase.gsub(/[[:punct:]]/, " ").downcase.split(" ")
    words.inject({}) do |counts, word|
      counts[word] ||= 0
      counts[word] += 1
      counts
    end
  end
end
