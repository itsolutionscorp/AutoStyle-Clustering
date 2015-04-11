class Phrase

  def initialize(the_phrase)
    @orig = the_phrase
    @pre_processed = preprocess(@orig)
  end

  def word_count
    wc = {}
    @pre_processed.each { |x| wc[x] = @pre_processed.select { |b| b==x }.count unless wc[x] }
    wc
  end

  private
  def preprocess(w)
    @pre_processed = strip_punctuation(w)
    @pre_processed = normalize(@pre_processed)
    @pre_processed = separate(@pre_processed)
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
