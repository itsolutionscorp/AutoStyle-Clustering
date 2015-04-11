class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = {}
    word_list = list_words
    word_list.each {|value| result[value] = word_list.count(value)}
    result
  end

  def list_words
    remove_empty_elements(remove_punctuation(@phrase.split(/[ ,]/)))
  end

  def remove_punctuation(word_list)
    word_list.map {|item| item.downcase.delete('^a-z0-9')}
  end

  def remove_empty_elements(word_list)
    word_list.reject(&:empty?)
  end
end
