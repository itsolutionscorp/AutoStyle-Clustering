class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    clean_string = format_string(@phrase)
    @counts ||= count_elements(clean_string.split)
  end

  private

  def format_string(string)
    string.downcase!
    remove_punctuation(string)
  end

  def remove_punctuation(string)
    pattern = %r{[^\w']}
    string.gsub(pattern, ' ')
  end

  def count_elements(array)
    keys = array.uniq
    keys.inject({}) do |hash, key|
      hash[key] = array.count(key)
      hash
    end
  end

end
