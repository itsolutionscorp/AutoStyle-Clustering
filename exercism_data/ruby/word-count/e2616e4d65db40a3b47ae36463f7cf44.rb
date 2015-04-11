class Phrase

  def initialize(phrase)
    @text = phrase.downcase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, counts| 
      counts[word] += 1 
    end
  end

  def word_list
    @text.scan(/\w+/)
  end

end
