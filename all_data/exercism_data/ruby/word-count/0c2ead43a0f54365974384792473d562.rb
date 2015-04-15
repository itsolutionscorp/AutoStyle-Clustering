class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, hash| hash[word] +=1 }
  end

  private

  def words
    @string.downcase.scan /\w+/
  end

end
