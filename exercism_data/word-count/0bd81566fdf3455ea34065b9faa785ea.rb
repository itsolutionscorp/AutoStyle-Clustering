class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @text = phrase
    @word_count = count_words 
  end

  def count_words
    words = @text.gsub(/,/, ' ').split " "
    counts = {}
    words.each do |w|
      next if (w =~ /[0-9a-zA-Z]/).nil? 
      w = w.gsub(/[^0-9a-zA-Z\' ]/i, '').downcase
      if counts.has_key? w 
        counts[w] += 1
      else
        counts[w] = 1
      end
    end
    counts
  end
end
