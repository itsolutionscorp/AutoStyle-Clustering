class Phrase
  def initialize(word)
    @word = word
  end
  
  def word_count
    words = @word.downcase.tr('^a-z1-9\\\'', ' ').split(' ')
    count = {}
    words.each do |w|
      count[w] = 0 if count[w].nil?
      count[w] += 1
    end
    return count
  end
end
