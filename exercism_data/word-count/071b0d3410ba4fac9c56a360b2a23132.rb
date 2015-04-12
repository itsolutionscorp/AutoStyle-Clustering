class Phrase
  attr_reader :text
  
  def initialize(text)
    @text = text
  end

  def word_count
    text.downcase.split(/\W+/).reduce(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end
end
