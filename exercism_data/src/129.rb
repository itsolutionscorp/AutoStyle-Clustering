class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array = @phrase.downcase.gsub(/[^A-Za-z0-9']/,' ').split
    frequency_hash = Hash.new(0)
    word_array.each do |word|
      frequency_hash[word] += 1
    end
    frequency_hash
  end
end
