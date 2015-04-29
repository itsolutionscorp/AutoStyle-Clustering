class Phrase < String

  def word_count
    word_counts = Hash.new(0)

    each_word do |word|
      word_counts[word] += 1
    end

    return word_counts
  end

  def each_word
    split(/[^a-zA-Z0-9]+/).each do |word| 
      yield word.downcase 
    end
  end

end
