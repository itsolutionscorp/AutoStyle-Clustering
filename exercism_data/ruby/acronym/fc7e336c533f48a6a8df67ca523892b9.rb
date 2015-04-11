class Acronym
  attr_reader :separate_words
  def self.abbreviate(word)
  	separate_words(word).map{ |w| w[0].upcase}.join
  end

  def self.separate_words(word)
  	word.scan(/[A-Z][a-z]+|\w+|\+-/)
  end
end
