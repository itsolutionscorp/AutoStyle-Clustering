class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    hash = {}
    list = word_list
    list.each {|value| hash[value] = list.count(value)}
    hash
  end

  def word_list
    remove_empty_elements(remove_punctuation(@phrase.split(/[ ,]/)))
  end

  def remove_punctuation(list)
    list.map {|item| item.downcase.delete('^a-z0-9')}
  end

  def remove_empty_elements(list)
    list.reject(&:empty?)
  end
end
