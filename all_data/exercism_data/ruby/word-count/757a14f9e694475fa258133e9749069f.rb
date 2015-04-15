class Phrase

  attr_reader :word_count

  def initialize phrase
    @phrase = remove_extraneous_chars phrase.downcase
    @word_count = {}
    populate_word_count(@phrase)
  end

  def add_word word
    if @word_count.has_key?(word)
      @word_count[word] += 1
    else
      @word_count[word] = 1
    end
  end

  def populate_word_count phrase
    phrase.split.each do |word|
      add_word word
    end
  end

  def remove_extraneous_chars string
    temp_string = string.gsub(/[,]/, " ")
    temp_string.gsub(/[^A-Za-z0-9 ]/, "")
  end
end
