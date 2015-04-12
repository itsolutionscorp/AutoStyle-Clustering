class Phrase
  def initialize(phrase_string)
    @phrase_string = phrase_string
  end


  def word_count
    counts = Hash.new(0) 

    @phrase_string.split(/\W/).select do |w| 
      w.strip != ""
    end.each do |word|
      word = word.downcase
      counts[word] += 1
    end

    counts
  end

end
