class Phrase
  def initialize word
    @word = word
  end

  def word_count
    word_to_count_mapping = Hash.new(0)
    sorted_array_of_words.each do |word|
      word_to_count_mapping[word] += 1
    end
    word_to_count_mapping
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
