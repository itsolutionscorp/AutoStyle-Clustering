class Phrase < String
  def word_count
    counts = Hash.new 0
    tokenize.each { |word| counts[word] += 1 }
    counts
  end

  def tokenize
    downcase.split(/\W+/)
  end
end
