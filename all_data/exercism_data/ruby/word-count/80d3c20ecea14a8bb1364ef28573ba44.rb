class Phrase
  def initialize(raw_str)
    @words = raw_str.gsub(/[^a-zA-Z0-9']/,' ').split.map { |word| word.strip.downcase }
  end

  def word_counter(word_arr)
    word_arr.each_with_object({}) { |word, h| h[word] = word_arr.count(word) }
  end

  def word_count
    word_counter(@words)
  end
end
