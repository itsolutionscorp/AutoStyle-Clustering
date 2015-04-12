class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/\s|,/)
    incidence = Hash.new {|hash, key| hash[key] = 0 }
    words.each do |word|
      word = word.sub(/(\!|\&|\@|\$|\%|\^|\&|\:|\.)+/, '').downcase
      unless word.empty? 
        incidence[word] += 1
      end
    end
    incidence
  end
end
