class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array = phrase.downcase.split(/[ ,]/)
    array_hash = {}
    word_array.each do |word|
      word.gsub!(/[^0-9a-z \']/, '')
      array_hash[word] ? array_hash[word] += 1 : array_hash[word] = 1 unless word.empty?
    end
    array_hash
  end
end
