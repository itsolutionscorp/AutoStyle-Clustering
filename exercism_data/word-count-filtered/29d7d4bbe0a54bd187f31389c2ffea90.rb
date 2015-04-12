class Phrase
  attr_reader :phrase_array, :count

  def initialize(phrase)
    @phrase_array = phrase.downcase.gsub(/[^0-9a-z']/i, ' ').split(' ')
  end

  def word_count
    @count = Hash.new(0)
    @phrase_array.each { |word| @count[word] = @count[word] + 1 }
    @count
  end
end
