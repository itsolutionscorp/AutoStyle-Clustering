Phrase = Struct.new(:phrase) do
  def word_count
    word_list = phrase.downcase.scan(/\w+/)

    word_list.inject(Hash.new { |h,k| h[k]=0 }) do |counts, word|
      counts[word] = counts[word] + 1
      counts
    end
  end
end
