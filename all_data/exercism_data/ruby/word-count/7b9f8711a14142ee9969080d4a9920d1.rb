class Words < String

  def count
    words.each_with_object(Hash.new(0)) {|word, table| table[word] += 1 }
  end

private

  def words
    self.downcase.split(/\W+/)
  end

end
