class Phrase
  
  def initialize (phrase)
    @phrase = phrase
    @hash = Hash.new
  end

  def word_count
    prepped_phrase = prep_phrase
    tmp_ar = prepped_phrase.split
    uniq_ar = tmp_ar.uniq
    uniq_ar.each do |word|
      @hash[word] = tmp_ar.count(word)
    end
    @hash
  end
  
  def prep_phrase
    str = @phrase.downcase.gsub(/\W+/,' ')
  end
end
