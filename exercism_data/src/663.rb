class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.scan(/[\w']+/)
    .each_with_object(Hash.new(0)) do |word, dict|
      dict[word.to_s.downcase] += 1
    end
  end
end
