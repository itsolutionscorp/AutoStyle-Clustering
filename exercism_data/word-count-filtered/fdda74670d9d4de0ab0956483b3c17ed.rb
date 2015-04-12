class Phrase
  
  def initialize(words)
    words.downcase.scan(/[\w']+/).each{ |word| word_count[word] += 1 }
  end

  def word_count
    @word_count ||= Hash.new(0)
  end
end
