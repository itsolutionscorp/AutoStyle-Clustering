class Phrase
  attr_accessor :word_count
  def initialize(words)
    self.word_count = Hash.new(0)
    count(words)
    remove_empty_string_from_count
  end

  private

  def count(words)
    words.split(" ").map{|w| word_count[cleaned_word(w)] += 1}
  end

  def cleaned_word(word)
    word.downcase.gsub(/[^\w]+/, '')
  end

  def remove_empty_string_from_count
    word_count.delete('')
  end
end
