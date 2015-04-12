module WordCheck

  def word_check(word)
    @count.has_key?(word) ? increase_count(word) : add_key(word)
  end

  def increase_count(word)
    @count[word] += 1
  end

  def add_key(word)
    @count[word] = 1
  end

end
