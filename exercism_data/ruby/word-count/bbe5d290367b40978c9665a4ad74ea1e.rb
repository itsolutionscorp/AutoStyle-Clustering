class Words < String

  def count
    words.each_with_object(Hash.new(0)) {|table, word| table[word] += 1 }
  end

private

  def words
    self
      .gsub(/[^0-9a-z ]/i, '')   # strip non-alphanumeric characters
      .split(' ')                # split into separate words
      .map(&:downcase)           # normalize
  end

end
