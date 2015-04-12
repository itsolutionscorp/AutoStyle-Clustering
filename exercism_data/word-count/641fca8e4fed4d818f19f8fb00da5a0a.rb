class Phrase
  def initialize(raw_str)
    @words = raw_str.gsub(/[^a-zA-Z0-9']/,' ').split.each do |word|
      word.strip!
      word.downcase!
    end
  end

  def word_counter(word_arr)
    counter = {}
    word_arr.each do |word|
      counter[word] = word_arr.count(word)
    end
    counter
  end

  def word_count
    word_counter(@words)
  end
end
