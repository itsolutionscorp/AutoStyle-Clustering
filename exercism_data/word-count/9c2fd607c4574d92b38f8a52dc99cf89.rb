class Words
  def initialize(in_string)
    @input_string = in_string
    @pairs = {}

    count_pairs
  end

  def count
    @pairs
  end

  private

  def count_pairs
    list_of_words.each {|word| add_word_to_counts word}
  end

  def add_word_to_counts(word)
    @pairs[word] = 0 unless @pairs.keys.include? word
    @pairs[word] += 1
  end

  def list_of_words
    clean_string = remove_punctuation @input_string
    normalize_case(clean_string).split
  end

  def normalize_case(in_string)
    in_string.downcase
  end

  def remove_punctuation(in_string)
    in_string.gsub(/\W/, ' ')
  end
end
