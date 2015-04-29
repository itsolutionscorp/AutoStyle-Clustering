class Phrase
  # keep intact word characters and apostrophe in the split
  WORD_SPLIT = /[^\w|\']/

  def initialize phrase_string
    @phrase_string = phrase_string
  end

  def word_count
    counts = Hash.new 0
    make_array.each do |word|
      counts[word] += 1
    end
    counts
  end

  # create a lowercase array of all the words (no empty)
  def make_array
    @phrase_string.downcase.split(WORD_SPLIT).reject(&:empty?)
  end

end
