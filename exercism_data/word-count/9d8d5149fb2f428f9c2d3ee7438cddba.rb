class Phrase
  def initialize word
    @word = word
  end

  def word_count
    sorted_array_of_words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def clean_word word
    word.downcase.gsub(/\W/, '')
  end

  def sorted_array_of_words
    @word.split(/[\s|,]/).sort.map do |word|
      word = clean_word word
      word unless  word.empty?
    end.compact
  end

end
