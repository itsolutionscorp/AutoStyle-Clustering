class Phrase
  def initialize(phrase_string)
    @phrase_string = phrase_string
  end


  def word_count
    Hash.new(0).tap do |hash|
      @phrase_string.split(/\W/).select do |w| 
        w.strip != ""
      end.each do |word|
        word = word.downcase
        hash[word] += 1
      end
    end
  end
end
