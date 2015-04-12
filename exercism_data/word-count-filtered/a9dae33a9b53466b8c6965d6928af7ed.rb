class Phrase

def initialize(phrase)
  @phrase = phrase  
end

def word_count
  regex = /\w+'?\w+|\d+/

  # get list of words
  words = @phrase.scan(regex).map { |i| i.downcase }

  # count words
  counts = words.map { |w| [w, words.count(w)] }

  # return in hash form
  Hash[counts]
end

end


s = Phrase.new("one of each")

p s.word_count
