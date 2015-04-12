class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    results = Hash.new(0)
    word_list.each do |word|
      results[word.downcase] += 1
    end

    results
  end

  private

  def word_list
    @phrase.scan(/\w+/)
  end
end
