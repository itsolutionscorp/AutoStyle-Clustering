class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array = prep_word_array
    word_count = Hash.new {0}
    word_array.each do |word|
      word_count[word] += 1
    end
    word_count
  end

  def prep_word_array
    word_array = @phrase.downcase.split(/\W/)
    word_array.select { |word| word[/\w/] }
  end

end
