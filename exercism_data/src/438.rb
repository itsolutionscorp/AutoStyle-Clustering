class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}
    @phrase.downcase.split(/[\s:!&@$%^,.]+/).each { |word| count[word] ? count[word] += 1 : count[word] = 1 }
    count
  end
end
