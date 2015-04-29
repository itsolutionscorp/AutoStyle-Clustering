# Phrase takes a string of words and
# counts how many times each word appears in the phrase.

class Phrase
  attr_reader :word_count

  def initialize phrase
    @word_count = {}
    phrase = phrase_splitter phrase
    phrase = normalize phrase
    phrase = punctuation_scrubber phrase

    phrase.each do |word|
      if @word_count.has_key?(word)
        @word_count[word] += 1
      elsif word != ''
        @word_count[word] = 1
      end
    end

    @word_count
  end

  def normalize phrase
    set = []
    phrase.each do |word|
      set << word.downcase unless word == '' or word == ':'
    end
    set
  end

  def phrase_splitter phrase
    phrase.split(/\b/)
  end

  def punctuation_scrubber phrase
    set = []
    phrase.each do |word|
      clean_word = word[/[a-z0-9]*/]
      set << clean_word
    end
    set
  end

end
