class Phrase < String

  def word_count
    word_counts = Hash.new(0)

    each_word do |word|
      word_counts[word] += 1
    end

    word_counts
  end

  def each_word
    downcase.split(/\W+/).each do |word| 
      yield word 
    end
  end

end
