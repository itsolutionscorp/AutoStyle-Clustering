class Phrase
  attr_reader :words
  def initialize(words="")
    @words = words
  end
  def word_count
    array_valid_chars.inject(Hash.new(0)) do |res, item|
      res[item] += 1
      res
    end
  end
  
  private 
  def array_valid_chars
    @words.downcase.scan(/[\w']+/)
  end

end
