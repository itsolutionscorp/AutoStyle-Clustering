class Phrase

  def initialize sentence
    @sentence = clean_sentence(sentence.downcase)
  end

  def word_count
    splitted_sentence.each_with_object(Hash.new(0)) do |word,hash|
      hash[word] += 1
    end
  end

  private

  def splitted_sentence
    @sentence.split(' ')
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


end
