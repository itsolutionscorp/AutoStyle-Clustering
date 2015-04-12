class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = phrase.downcase.split(/[^\w']/) # regex matches any non alphanumeric characters except apostrophes

    words.each_with_object({}) do |word, hsh|
      hsh[word] = words.count { |w| word.eql? w } unless word.empty?
    end
  end

end
