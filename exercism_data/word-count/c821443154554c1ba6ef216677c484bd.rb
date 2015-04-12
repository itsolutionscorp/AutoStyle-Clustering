class Phrase

  def initialize(the_phrase)
    @the_phrase = the_phrase
  end

  def word_count
    words = words_from_phrase(["strip_punctuation", "normalize" , "separate"])
    words.each_with_object({}) { |x, wc| wc[x] = words.select { |b| b==x }.count }
  end

  private
  def words_from_phrase( rules )
    result = @the_phrase
    rules.each do |r|
      result = send(r, result)
    end  
    result
  end

  def strip_punctuation(w)
    w.tr_s('!&@$%^:', '')
  end

  def normalize(w)
   w.downcase
  end

  def separate(w)
   w.tr_s(',', ' ').split(" ")
  end

end
