class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @text = phrase
    @word_count = count_words 
  end

  def count_words
    words = to_array_of_words(@text)
    counts = {}
    words.each do |w|
      next if has_no_alphanumeric(w)
      w = to_lowercase(w)
      if counts.has_key? w 
        counts[w] += 1
      else
        counts[w] = 1
      end
    end
    counts
  end

  def to_array_of_words(text)
    text.gsub(/,/, ' ').split " "
  end

  def has_no_alphanumeric(text)
    (text =~ /[0-9a-zA-Z]/).nil? 
  end

  def to_lowercase(text)
    text.gsub(/[^0-9a-zA-Z\' ]/i, '').downcase
  end
end
