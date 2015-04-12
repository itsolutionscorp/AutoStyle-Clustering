class Phrase
  def initialize string
    @string = string
  end

  def word_count
    words = empty_word_hash
    word_list.each { |word| words[word] += 1 }

    words
  end

  def to_s
    @string
  end

  def word_list
    to_s.downcase.split(/\W+/)
  end

  private
  def empty_word_hash
    Hash.new { |hash, word| hash[word] = 0 }
  end
end
