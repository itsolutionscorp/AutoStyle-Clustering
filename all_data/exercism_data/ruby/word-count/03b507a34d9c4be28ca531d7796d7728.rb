Phrase = Struct.new(:words) do
  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def word_list
    words.downcase.scan(/\w+/)
  end
end
