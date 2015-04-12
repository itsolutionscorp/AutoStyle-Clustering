class Phrase

attr_reader :phrase  # => nil

def initialize (phrase)
  @phrase = phrase
end 

def word_count

  phrase.downcase.scan(/[\w']+/).each_with_object(Hash.new(0)) { |v, h| h[v] += 1 }
  
end

end
