class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.gsub(/[,\.;:!&@$%\^]/, ' ').split(' ')
    words.reduce(Hash.new(0)) do |word_count, word|
      word_count.merge(word.downcase => word_count[word.downcase] + 1)
    end
  end
  # def word_count
  #   words = @phrase.gsub(/[,\.;:!&@$%\^]/, ' ').split(' ')
  #   counts = Hash.new(0)
  #   words.each do |word|
  #     counts[word.downcase] += 1
  #   end
  #   counts
  # end

end
