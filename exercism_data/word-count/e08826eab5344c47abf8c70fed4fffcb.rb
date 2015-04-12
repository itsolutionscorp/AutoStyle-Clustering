Phrase = Struct.new(:phrase) do
  def word_count
    words = phrase.scan(/\w+/).map &:downcase

    words.each_with_object Hash.new(0) do |word, result|
      result[word] += 1
    end
  end
end
