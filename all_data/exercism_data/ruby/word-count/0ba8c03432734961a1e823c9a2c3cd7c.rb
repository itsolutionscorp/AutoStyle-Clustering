class Phrase < String
  DELIMITERS = /[ ,!&@$%^:.]+/
  
  def word_count
    self.downcase.split(DELIMITERS).reduce(Hash.new(0)) do |wc, word|
      wc[word] += 1
      wc
    end
  end
end
