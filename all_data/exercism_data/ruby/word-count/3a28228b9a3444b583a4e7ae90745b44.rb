class Phrase
  attr_reader :passage
  
  def initialize(phrase)
    @passage = phrase.downcase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, counts| 
      counts[word] += 1 
    end
  end

  def word_list
    @passage.scan(/\w+/)
  end

end
