class Phrase
  attr_accessor :text
  
  def initialize(text)
    self.text = text
  end
  
  def words
    text.downcase.scan(/\w+/)
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
