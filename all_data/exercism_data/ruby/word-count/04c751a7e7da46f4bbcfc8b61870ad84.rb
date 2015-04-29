class Phrase

  def initialize string
    @string = string
  end

  def word_count
    @string.scan(/\w+'\w|\w+/).inject(Hash.new 0) { |set, word| set[word.downcase] += 1; set }
  end

end
