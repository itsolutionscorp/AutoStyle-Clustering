# phrase.rb
class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    matches.each_with_object(Hash.new(0)) do | match, hash |
      hash[match.downcase] += 1
    end
  end

  def matches
    phrase.scan(/[\p{Latin}0-9']+/)
  end
end
