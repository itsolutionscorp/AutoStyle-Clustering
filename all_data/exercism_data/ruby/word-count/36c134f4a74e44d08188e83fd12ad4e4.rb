Phrase = Struct.new(:text) do

  def word_count
    words = text.downcase.split(/\W+/)
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] +=1 }
  end

end
