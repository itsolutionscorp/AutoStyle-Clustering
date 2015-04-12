Phrase = Struct.new(:text) do

  def word_count
    words = parse_words
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word] +=1 }
  end
  
  def parse_words
    text.downcase.split(/\W+/)
  end

end
