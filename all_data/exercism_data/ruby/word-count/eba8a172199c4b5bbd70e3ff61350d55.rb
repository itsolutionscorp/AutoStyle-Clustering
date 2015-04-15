class Phrase

  def initialize(text)
    @text = text
  end

  def word_count

    @text.downcase!
    words = @text.scan(/[[:alnum:]]+/)
    
    word_hash = Hash.new

    words.each do |word|
      word_hash[word] = words.count(word)
    end
    word_hash

  end

end
