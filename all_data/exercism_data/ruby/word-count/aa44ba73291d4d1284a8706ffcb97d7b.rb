class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    @_word_count ||= word_list.uniq.each_with_object({}) do |word,counter|
      counter[word] = word_list.count(word)
    end
  end

  def word_list
    @_word_list ||= @phrase.downcase.scan(/\w+/)
  end

end
