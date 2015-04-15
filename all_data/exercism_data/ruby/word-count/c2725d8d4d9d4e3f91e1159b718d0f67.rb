class Phrase 

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counted_hash(words)
  end

  private
  def words
    downcased_list(strip_non_alpha(@phrase))
  end

  def strip_non_alpha(string)
    string.gsub(/[^0-9a-z ]/i, '')
  end

  def downcased_list(string)
    string.downcase.split(' ')
  end

  def counted_hash(words)
    words.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end
end
