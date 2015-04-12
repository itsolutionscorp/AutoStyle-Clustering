class Phrase

  SPLITTER = /[^\w']+/

  def initialize string
    @string = string
  end

  def word_count
    word_hash = Hash.new(0)
    @string.downcase.split(SPLITTER).each { |word| word_hash[word] += 1}
    word_hash
  end

end
