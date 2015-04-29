class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    counts = Hash.new {|hash, key| hash[key] = 0 }

    words.each do |word|
      counts[word] += 1 if word
    end

    counts
  end

  def words
    @phrase.scan(/[a-z|A-Z|0-9|\']+/)
  end
end
