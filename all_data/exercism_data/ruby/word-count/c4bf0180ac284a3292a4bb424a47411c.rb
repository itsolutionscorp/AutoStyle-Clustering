class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}
    words = clean(@phrase).split(' ')

    words.each do |word|
      count = words.count(word)
      counts[word] = count
    end
    counts
  end

  def clean(phrase)
    @phrase.downcase!
    @phrase.gsub!(/[^[:word:](?i')]/, ' ')
    @phrase
  end

end
