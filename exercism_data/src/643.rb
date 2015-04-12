class Phrase

  def initialize(phrase)
    self.words = phrase.scan(/[\w']+/).map(&:downcase)
  end

  def word_count
    counter = Hash.new {|hash, key| hash[key] = 0}
    words.inject(counter) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  private

  attr_accessor :words
end
