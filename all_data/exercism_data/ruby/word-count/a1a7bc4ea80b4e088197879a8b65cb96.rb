class Phrase

  def initialize phrase
    @words = phrase.downcase.gsub(/\W+/, ' ').split(" ")
  end

  def word_count
    results = Hash.new(0)

    @words.each do |word|
      results[word] += 1
    end

    results
  end
end
