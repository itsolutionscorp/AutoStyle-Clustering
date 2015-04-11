class Phrase
  def initialize(string)
    @input = string
  end

  def groom_words
    @input.downcase.gsub( /[^\w\s]/, ' ' ).strip.split
  end
    
  def word_count
    groomed_words = groom_words
    groomed_words.uniq.each_with_object({}) { |word, hash| hash[word] = groomed_words.count(word) }
  end
end
