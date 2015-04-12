class Phrase

  def initialize(input = '')
    @phrase = input
  end

  def word_count
    #create an array of words, splitting on non-word characters
    words = @phrase.downcase.split(%r(\W+))
    #copy of unique words
    distinct_words = words.uniq
    @counts = Hash.new
    #iterate through distinct words
    distinct_words.each do |word|
      #add counts to hash
      @counts[word] = words.count word
    end
    return @counts
  end
end
