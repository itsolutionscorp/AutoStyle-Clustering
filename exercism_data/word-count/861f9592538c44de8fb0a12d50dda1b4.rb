class Phrase
  def initialize(word)
    @word = word
  end

  def word_count
    word_arr = create_word_array
    count_words(word_arr)
  end

  def create_word_array
    @word.gsub(",", " ").
          downcase.delete(",.:").
          split(' ')
  end

  def count_words(word_arr)
    word_hsh = Hash.new(0)
    word_arr.each do |word|
      word = word.delete("^/[a-z0-9]\'/")
      word_hsh[word] += 1
    end
    word_hsh
  end
end
