class Phrase

  def initialize(text)
    @text = text.downcase
  end

  def word_count
    array = @text.scan(/[[:alnum:]]+/)
    hash = Hash.new
    array.each do |word|
      hash[word] = array.count(word)
    end
    hash

  end

end
