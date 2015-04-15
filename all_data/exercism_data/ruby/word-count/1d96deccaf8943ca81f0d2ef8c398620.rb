class Phrase

  attr_reader  :words

  def initialize words
    @words =  words
  end

  def word_count
      to_array(words).each_with_object(Hash.new(0)) { |o, h| h[o] += 1 }  
  end

  private

  def  to_array words
    words.downcase.gsub(/[^a-z\d\s]/, '').split
  end

end
