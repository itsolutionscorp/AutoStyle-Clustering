class Phrase

  def initialize(input)
    @hash = {} 
    @hash.default = 0
      #finds word characters, plus an optional apostrophe and lowercase character for edge case of "don't", etc. 
    input.scan(/\w+'?[a-z]?/).each do |word|
      @hash[word.downcase] += 1
    end
  end

  def word_count
    @hash
  end

end
