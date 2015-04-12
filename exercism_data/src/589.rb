class Phrase
  def initialize(phrase)
    @phrase = phrase.gsub(/[[[:punct:]]^$]+/, ' ')
  end

  def word_count
    word_list = @phrase.split
    results = Hash.new(0)

    word_list.each do |word|
      results[word.downcase] += 1
    end

    results
  end
end
