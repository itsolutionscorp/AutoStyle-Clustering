Phrase = Struct.new(:phrase) do

  def word_count
    counts = Hash.new(0)
    words = phrase.downcase.scan(/([\w']+)/).flatten
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

end
