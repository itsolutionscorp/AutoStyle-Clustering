class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    @phrase.gsub(/[^a-zA-Z0-9']/, ' ').split(' ').each do |word|
      count[word.downcase] += 1
    end
    count
  end
end
