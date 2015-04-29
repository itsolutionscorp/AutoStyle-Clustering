class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[^a-z'\d\s]/, ' ').split
  end

  def word_count
    word_hash ={}
    @phrase.each do |word|
      word_hash[word].nil? ? word_hash[word] = 1 : word_hash[word] += 1
    end
    word_hash
  end
end
