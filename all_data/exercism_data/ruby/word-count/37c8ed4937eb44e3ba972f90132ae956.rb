class Phrase

  def initialize(phrase)
    self.phrase = phrase
  end
  
  def word_count
    if !words_with_count
      self.words_with_count = calculate_word_count
    end
    words_with_count
  end

  private
  attr_accessor :words_with_count, :phrase
  
  def calculate_word_count
    extract_words_from(phrase).each_with_object(Hash.new(0)) { |word, counter| counter[word] += 1 }
  end

  def extract_words_from(phrase)
    phrase.downcase.scan /\w+/
  end
end
