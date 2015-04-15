class Phrase

  def initialize(phrase)
    @words = phrase.gsub(/[^a-zA-Z0-9]/,' ').split(' ').map(&:downcase)
  end

  def word_count
    result = {}
    @words.each{|w| result[w] = 0}
    @words.each{|w| result[w] += 1}
    result
  end
end
