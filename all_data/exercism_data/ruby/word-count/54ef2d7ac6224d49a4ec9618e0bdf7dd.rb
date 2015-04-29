class Phrase
  def initialize( phrase )
    @phrase = generate_clean_word_list(phrase)
  end

  def word_count
    counted_words = {}

    @phrase.each do |word|
      counted_words.has_key?(word) ? counted_words[word] += 1 : counted_words[word] = 1
    end

    counted_words
  end

  private
  def generate_clean_word_list(phrase)
    phrase = split_phrase(phrase)
    cleaned_word_array = []

    phrase.each do |word|
      clean_word = remove_punctuation( word )
      cleaned_word_array << clean_word unless clean_word.empty?
    end

    cleaned_word_array
  end

  def split_phrase( phrase )
    phrase.split(/[\s,]+/)
  end

  def remove_punctuation( word )
    word.downcase.gsub(/[^0-9a-z']+/i, '')
  end
end
