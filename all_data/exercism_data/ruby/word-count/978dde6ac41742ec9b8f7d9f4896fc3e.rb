class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    words = entries_for downcase @string
    words.each_with_object(Hash.new(0)) {|word, hash| hash[word] +=1 }
  end

  private

  def downcase(string)
    string.downcase
  end

  def entries_for(string)
    string.scan /\w+/
  end

end
