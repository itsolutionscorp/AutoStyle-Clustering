require 'facets'

class Phrase

  def initialize(phrase)
    @words = phrase.downcase.tr('^A-Za-z0-9,\' ', '').tr(',', ' ').split(" ")
  end

  def word_count
   @words.frequency
  end

end
