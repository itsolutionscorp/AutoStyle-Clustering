class Phrase
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
    duplicates = word_count
    p duplicates
  end

  def word_count 
    words = splits
    count_duplicate words
  end

  private

  def splits 
    words = @phrase.split(/[\s,]+/) 
  end

  def count_duplicate words
    duplicates = Hash.new(0)
    words.select {|word| word.match(/\w/)}.map {|word| duplicates[word.downcase] += 1 }
    duplicates
  end

end

phrase = 'one fish,two,fish red fish blue fish'
word_count = Phrase.new(phrase)
puts word_count
