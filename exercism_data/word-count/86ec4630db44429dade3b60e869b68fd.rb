class Word_count
  attr_reader :phrase

  def show_duplicate_words_in phrase
    words = splits phrase
    count_duplicate words
  end

  private

  def splits phrase
    phrase.split(/[\s,]+/) 
  end

  def count_duplicate words
    duplicates = Hash.new(0)
    words.each {|word| duplicates[word] += 1 }
    duplicates
  end

end

# phrase = 'one fish,two,fish red fish blue fish'
# word_count = Word_count.new
# duplicate_words = word_count.show_duplicate_words_in(phrase)
# puts duplicate_words
