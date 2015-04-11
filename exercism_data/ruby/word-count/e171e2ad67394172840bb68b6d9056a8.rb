class Phrase < String

  def word_count
    count = Hash.new(0)
    words.each {|word| count[word] += 1}
    count
  end

  private

  def words
    downcase.split(/\W+/)
  end

end
