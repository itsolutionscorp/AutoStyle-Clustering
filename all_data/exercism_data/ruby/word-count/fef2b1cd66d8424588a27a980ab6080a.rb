class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[[:punct:]]/,' ')
  end

  def word_count
    @phrase.split(/\W+/).reduce(Hash.new(0)) { |dict, w| dict[w] += 1; dict } 
  end

end
