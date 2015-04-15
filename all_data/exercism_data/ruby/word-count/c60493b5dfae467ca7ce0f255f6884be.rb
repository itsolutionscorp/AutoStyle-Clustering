Phrase = Struct.new(:phrase) do
  def word_count
    word_list = phrase.downcase.scan(/\w+/)

    word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] = counts[word] + 1
    end
  end
end
