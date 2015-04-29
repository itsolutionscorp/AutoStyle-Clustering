class Phrase
  def initialize(word)
    @word = word
  end

  def word_count
    word = @word.gsub(",", " ").downcase.delete(",.:")
    word_arr = word.split(" ")
    word_hsh = Hash.new(0)

    word_arr.each do |word|
      word = word.delete("^/[a-z0-9]\'/")
      word_hsh[word] += 1
    end

    word_hsh
  end
end
