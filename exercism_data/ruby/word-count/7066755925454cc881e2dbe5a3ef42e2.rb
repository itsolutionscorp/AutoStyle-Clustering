class Phrase
  def initialize(phrase)
    @words = clean_up_phrase(phrase)
  end

  def word_count
    grouped_words = @words.group_by {|x| x}
    grouped_words.each {|k,v| grouped_words[k] = v.count}
  end

  private
  def clean_up_phrase(phrase)
    phrase.downcase.split(/\W+/)
  end
end
