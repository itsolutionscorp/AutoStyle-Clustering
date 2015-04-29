class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    counts = Hash.new(0)

    words.each do |word|
      counts[word.downcase] += 1
    end

    counts
  end

  private
  def words
    @words.split(/\W/).delete_if(&:empty?)
  end
end
