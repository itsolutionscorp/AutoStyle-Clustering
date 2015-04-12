class Phrase

  def initialize(text)
    @text = text
  end

  def split_to_words(test)
    @text.downcase
      .split(/[^a-zA-Z0-9\']/)
      .select{|x| not x.empty?}
  end

  def word_count
    split_to_words(@text)
      .reduce(Hash.new(0)) {|x,y| x[y] += 1; x }
  end
end
