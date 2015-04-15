class Phrase
  def initialize(phrase)
    @words = clean_up_phrase(phrase)
  end

  def word_count
    word_occurance_count
  end

  private
  def clean_up_phrase(phrase)
    phrase.gsub(/\W+/, ' ').squeeze(' ').downcase.split
  end

  def word_occurance_count
    grouped_words = @words.group_by {|x| x}
    grouped_words.each {|k,v| grouped_words[k] = v.count}
  end
end
