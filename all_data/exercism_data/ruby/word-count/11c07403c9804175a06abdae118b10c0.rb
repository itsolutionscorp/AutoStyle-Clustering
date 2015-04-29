Phrase = Struct.new(:sentence) do
  def word_count
    words.each_with_object(Hash.new 0) { |word,counts| counts[word] += 1 }
  end

  def words
    sentence.downcase.scan /[\w']+/
  end
end
