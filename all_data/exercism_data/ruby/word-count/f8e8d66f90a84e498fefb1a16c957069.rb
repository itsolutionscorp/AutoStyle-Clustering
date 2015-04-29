class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    create_hash
  end

  def split_words
    @phrase.split(/[ ,]/)
  end

  def remove_punctuation(list)
    (list.map {|item| item.downcase.delete('^a-zA-Z0-9')}).reject(&:empty?)
  end

  def create_hash
    array = remove_punctuation(split_words)
    hash = {}
    array.each do |value|
      hash[value] = array.count(value)
    end
    return hash
  end
end
