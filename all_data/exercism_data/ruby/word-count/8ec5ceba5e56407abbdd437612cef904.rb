class Phrase

  NON_WORD_CHARS = /[^a-z0-9']+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse_into_words.group_by{|word|word}.map do |word, instances|
      [word, instances.count]
    end.to_h
  end

  def parse_into_words
    @phrase.downcase.gsub(NON_WORD_CHARS, ' ').split
  end

end
