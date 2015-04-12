class Phrase
  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
  end    

  def word_count
    counts = {}

    @words.each do | word |
      counts[word] = 0 if counts[word].nil?
      counts[word] += 1
    end

    return counts
  end
end
