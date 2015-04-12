class Phrase
  attr_accessor :word_count

  def initialize(word)
    @word_count = convert_to_words_hash(word.downcase)
  end

  def convert_to_words_hash(word)
    arr = word.split(/[\s,]+/)
    word_hash = {}
    arr.each do |word|
      word = word.gsub(/[^[a-z][0-9]]+$/i, "")
      word_hash.has_key?(word) ? word_hash[word] += 1 : word_hash[word] = 1 unless word == ""
    end
    word_hash
  end
end
