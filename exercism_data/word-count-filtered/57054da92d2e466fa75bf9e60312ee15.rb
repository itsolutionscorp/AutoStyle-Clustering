class Phrase
  def initialize(text)
  	@text = text
  end

  def word_count
  	words = @text.split /[^\w\d]+/
  	words.inject({}) do |hash, word|
      key = word.downcase
  		current_count = hash.fetch(key, 0)
  		hash.merge key => current_count.next
  	end
  end
end
