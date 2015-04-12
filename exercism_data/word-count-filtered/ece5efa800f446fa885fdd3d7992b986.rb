class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase = @phrase.gsub(/[^a-z0-9 ]/i, ' ').downcase.split(/ +/)
    count = Hash.new 0
    phrase.each do |d|
      count[d] += 1
    end
    return count
  end
end
