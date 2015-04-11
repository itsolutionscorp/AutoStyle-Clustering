class String
  def as_whole_words
    self.split(/\W/).select {|w| !w.strip.empty?}
  end
end

class Phrase
  def initialize(phrase_string)
    @phrase_string = phrase_string
  end


  def word_count
    Hash.new(0).tap do |hash|
      @phrase_string.as_whole_words.each do |word|
        word = word.downcase
        hash[word] += 1
      end
    end
  end
end
