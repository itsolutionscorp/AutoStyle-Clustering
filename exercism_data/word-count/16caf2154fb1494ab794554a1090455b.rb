class Phrase
  def initialize(words, frequency_counter=Frequencies.new, words_cleaner=WordsCleaner.new)
    @words = words
    @frequency_counter = frequency_counter
    @words_cleaner = words_cleaner
  end

  def word_count
    cleaned_words = @words_cleaner.clean @words
    @frequency_counter.frequencies_for cleaned_words
  end
end

class WordsCleaner
  def clean words
    alpha_numeric_only(words).downcase.split
  end

  def alpha_numeric_only words
    words.gsub(/[^a-z0-9]/i, ' ')
  end
end

class Frequencies
  def frequencies_for tokens
    tokens.group_by {|e| e }.each_with_object({}) do |(word, occurs), result|
      result[word] = occurs.length
    end
  end
end
