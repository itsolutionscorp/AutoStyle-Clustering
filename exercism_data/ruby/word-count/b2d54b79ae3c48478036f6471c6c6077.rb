class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    @phrase.inject(Hash.new(0)) do |base, curr|
      base[curr] += 1
      base
    end
  end
end
