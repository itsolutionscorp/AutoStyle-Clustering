class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    cleaned_phrase.inject Hash.new(0) do |hash, key|
      hash[key] += 1
      hash
    end
  end

  private

  attr_reader :phrase

  def cleaned_phrase
    phrase.downcase.gsub(/\W/, ' ').split
  end
end
