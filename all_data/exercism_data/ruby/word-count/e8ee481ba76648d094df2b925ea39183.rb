class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words
  end

  def word_count
    prep_words_for_counting()
    word_list = {}
    words.split(" ").each do |word|
      word_list = process_word_in_list(word, word_list)
    end
    word_list
  end

private
  def process_word_in_list(word, word_list)
    word = parse_word(word)
    return word_list if word.empty?
    word_list[word] ||= 0
    word_list[word] += 1
    word_list
  end

  def prep_words_for_counting
    words.gsub!(",", " ")
  end

  def parse_word(word)
    word.gsub!(/[^a-zA-Z0-9]/, "")
    word.downcase!
    word
  end

end
