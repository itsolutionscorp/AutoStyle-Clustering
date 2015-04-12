class Phrase

  def initialize(string)
    @phrase = string
    @counts = {}
  end

  def word_count
    word_list = parsed_array(@phrase)
    count_words(word_list)
    @counts
  end

  private

  def parsed_array(string)
    string.downcase!
    string.split(%r{[!@$%^&:,.\s]+})
  end

  def count_words(array)
    keys = array.uniq
    keys.each do |key|
      @counts[key] ||= array.count(key)
    end
  end

end
