class Phrase

  def initialize(text)
    @text = text
  end

  def word_count

    @text.downcase!
    words = @text.scan(/[[:alnum:]]+/)


    words.each_with_object({}) do |word, hash|
      hash[word] = words.count(word)
    end


  end

end
