class Phrase

  def initialize sentence
    @sentence = clean_sentence(sentence.downcase)
  end

  def clean_sentence(sentence)
    sentence = replace_coma_by_space(sentence)
    sentence = remove_punctuation(sentence)
  end

  def remove_punctuation(sentence)
    sentence.gsub(/[^a-zA-Z0-9\,\'\ ]+/, '')
  end

  def replace_coma_by_space(sentence)
    sentence.gsub(/\,/, ' ')
  end

  def word_count
    hash = Hash.new(0)
    @sentence.split(' ').each do |word|
      hash[word] += 1
    end
    hash
  end

end
