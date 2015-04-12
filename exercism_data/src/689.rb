class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    @string.downcase!
    word_hash = Hash.new(0)
    words = @string.split(/[^a-zA-Z0-9']/)

    words.each do |word|
      word_hash[word] += 1
    end
    word_hash.delete("")
    word_hash
  end

end
