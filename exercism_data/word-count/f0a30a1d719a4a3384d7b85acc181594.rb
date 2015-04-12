class Phrase < String

  def word_count
    words.each_with_object Hash.new(0) do |word, histogram|
      histogram[word] += 1
    end
  end

  def words
    downcase.split /\W+/
  end

end
