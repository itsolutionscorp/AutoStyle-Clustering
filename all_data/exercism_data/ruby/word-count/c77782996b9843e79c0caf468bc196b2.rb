class Phrase 

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = strip_non_alpha(@phrase)
    counts = {}
    words.downcase.split(' ').each do |word|
      if counts.has_key? word
        counts[word] += 1
      else
        counts[word] = 1
      end
    end
    counts
  end

  private
  def strip_non_alpha(string)
    string.gsub(/[^0-9a-z ]/i, '')
  end

end
