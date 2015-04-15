class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_hash = Hash.new(0)
    prepare_phrase_for_processing.split(' ').each do |w|
        word_hash[w] = word_hash[w] + 1
    end
    word_hash
  end

  def prepare_phrase_for_processing
    phrase.downcase.gsub(/\W+/, ' ')
  end


end
