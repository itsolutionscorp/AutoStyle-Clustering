class Phrase < String

  def word_count
    add_word_to_count = -> (hash, word) { hash[word] += 1; hash;  }
    words.reduce(Hash.new(0), &add_word_to_count)
  end

  def words
    scan(/\w+/).map{|w|w.downcase}
  end

end
