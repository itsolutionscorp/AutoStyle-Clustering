class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  def word_count
    @phrase.downcase!
    @phrase.gsub!(/[^0-9a-z,' ]/i, '')
    @phrase.gsub!(',', ' ')
    @phrase.gsub!('  ', ' ')
    word_array = @phrase.split
    out = Hash.new
    word_array.each do |elem|
      if out[elem] == nil
        out[elem] = 1
      else
        out[elem] = out[elem] + 1
      end
    end
    out
  end
end
