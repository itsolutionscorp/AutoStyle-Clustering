class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    Hash[counted_words]
  end

  private
  def words
    @sentence.downcase.split(/\p{^Word}/).reject(&:empty?)
  end

  def grouped_words
    words.group_by(&:to_s)
  end

  def counted_words
    grouped_words.map{|k,v| [k, v.count]}
  end
end
