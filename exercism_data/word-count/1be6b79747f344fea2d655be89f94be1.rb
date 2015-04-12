class Phrase
  def initialize(phrase)
    @phrase = phrase.gsub(/[!&@$%^:,.]/, ' ').downcase
  end

  def word_count
    @phrase.split.inject(Hash.new(0)) do |counts, word|
      counts.merge word => counts[word] + 1
    end
  end
end
