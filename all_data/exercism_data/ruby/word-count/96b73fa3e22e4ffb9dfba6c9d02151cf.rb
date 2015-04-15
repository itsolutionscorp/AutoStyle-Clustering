class Phrase

  PHRASE_SPLITTER = Regexp.new /\b/
  WORD_VALIDATOR  = Regexp.new /\w+/

  def initialize(phrase)
    @phrase = phrase
    @words = identify_words_in_phrase
    @word_count_info = build_word_count_info
  end

  def word_count
    @word_count_info
  end


  private

  def split_phrase(phrase)
    phrase.split(PHRASE_SPLITTER)
  end

  def valid_words_within(words)
    words.select{ |word| word =~ WORD_VALIDATOR }
  end

  def downcased(words)
    words.map{ |word| word.downcase }
  end

  def identify_words_in_phrase
    downcased( valid_words_within( split_phrase(@phrase) ) )
  end

  def build_word_count_info
    count_info = Hash.new(0)
    @words.map{ |word| count_info[word] += 1 }
    count_info
  end

end
