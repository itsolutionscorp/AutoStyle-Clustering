class Phrase 

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = words(@phrase)
    make_counted_hash(words)
  end

  private
  def words(string)
    make_list(strip_non_alpha(string))

  end
  def strip_non_alpha(string)
    string.gsub(/[^0-9a-z ]/i, '')
  end

  def make_list(string)
    string.downcase!
    string.split(' ')
  end

  def make_counted_hash(words)
    counts = Hash.new(0)
    words.each { |word| counts[word] += 1 }
    counts
  end
end
