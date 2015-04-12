class Phrase

  attr_reader :input

  def initialize(input = "")
    @input = input
  end

  def word_count
    words = prep_input(input)
    counts = collect_word_counts(words)
  end

private

  def collect_word_counts(words)
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    return counts
  end

  def prep_input(input)
    clean_input = remove_punctuation(input)
    split_words(clean_input)
  end 

  def remove_punctuation(dirty_phrase)
    dirty_phrase.downcase.gsub(/[^a-z0-9\s,]/,"")
  end
  
  def split_words(clean_input)
    clean_input.split(/[\s,]+/)
  end

end
