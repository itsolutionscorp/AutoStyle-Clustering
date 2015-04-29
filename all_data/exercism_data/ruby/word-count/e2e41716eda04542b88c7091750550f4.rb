class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/\s|,/)
    incidence = Hash.new
    words.each do |word|
      word = word.sub(/(\!|\&|\@|\$|\%|\^|\&|\:|\.)+/, '').downcase
      unless word.empty? 
        incidence[word] = 0 unless incidence.has_key?(word)
        incidence[word] += 1
      end
    end
    incidence
  end
end
