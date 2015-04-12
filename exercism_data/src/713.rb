class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[[:punct:]]/,' ')
  end

  def word_count
    @phrase.split(/\W+/).each_with_object(Hash.new(0)) { |w,dict| dict[w] += 1 } 
  end

end
