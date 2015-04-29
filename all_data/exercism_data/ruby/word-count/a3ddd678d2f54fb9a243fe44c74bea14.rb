class Phrase

  def initialize phrase
    @phrase = phrase
    @words = homogenize.split
  end

  def word_count
    counts = Hash.new(0)

    @words.each do |word|
      counts[word] += 1
    end
    counts
  end

  def homogenize
    @phrase.downcase.gsub(/[^a-z\s0-9]/, '')
  end
end
