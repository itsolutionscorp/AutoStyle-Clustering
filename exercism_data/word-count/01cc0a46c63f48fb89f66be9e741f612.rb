class Phrase
  attr_reader :phrase
  
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    @phrase.split(/\W/).delete_if(&:empty?).each_with_object(Hash.new(0)) do |word, h|
      h[word.downcase] += 1
    end
  end
end
