class Acronym
  attr_reader :separate_words
  def self.abbreviate(word)
  	separate_words(word).map{ |w| w[0].upcase}.join
  end

  def self.separate_words(word)
  	word.scan(/\w+|\+-/).map{|w| w.split /(?=[A-Z][a-z])/ }.flatten
  end
end
