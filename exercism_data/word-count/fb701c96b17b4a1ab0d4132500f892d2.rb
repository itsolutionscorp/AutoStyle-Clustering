class Phrase 

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = strip_non_alpha(@phrase)
    words = make_list(words)
    make_counted_hash(words)
  end

  private
  def strip_non_alpha(string)
    string.gsub(/[^0-9a-z ]/i, '')
  end

  def make_list(string)
    string.downcase!
    string.split(' ')
  end

  def make_counted_hash(words)
    counts = Hash.new
    words.each do |word|
      if counts.has_key? word
        counts[word] += 1
      else
        counts[word] = 1
      end
    end
    counts
  end
end
