class Phrase

  def initialize(the_phrase)
    @the_phrase = the_phrase
    preprocess
  end

  def word_count
    @the_phrase.each_with_object({}) { |x, wc| wc[x] = @the_phrase.select { |b| b==x }.count }
  end

  private
  def preprocess
    strip_punctuation(@the_phrase)
    normalize(@the_phrase)
    separate(@the_phrase)
  end

  def strip_punctuation(w)
    @the_phrase = w.tr_s('!&@$%^:', '')
  end

  def normalize(w)
    @the_phrase = w.downcase
  end

  def separate(w)
    @the_phrase = w.tr_s(',', ' ').split(" ")
  end


end
