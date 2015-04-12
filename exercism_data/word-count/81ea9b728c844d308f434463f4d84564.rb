class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    @phrase.split(/[!@$%&,.;: ^]/)
           .select { |w| w != "" }
           .map    { |w| w.downcase }
           .each   { |w| result[w] = result[w] + 1 }
    result
  end
end
