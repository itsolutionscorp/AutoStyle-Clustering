class WordBank

  def initialize(phrase)
    _fill_bank phrase
  end

  def word_count
    @bank
  end

  private

  def _fill_bank(phrase)
    @bank = {}
    phrase.split(/[, ]/).each do |word|
      word = _clean_word word
      _add_word(word) if not word.empty?
    end
  end

  def _add_word(word)
    if @bank.has_key? word
      @bank[word] += 1
    else
      @bank[word] = 1
    end
  end

  def _clean_word(word)
    word.gsub(/[^0-9a-zA-Z']+/, '').downcase
  end
end

class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordBank.new(@phrase).word_count
  end

end
