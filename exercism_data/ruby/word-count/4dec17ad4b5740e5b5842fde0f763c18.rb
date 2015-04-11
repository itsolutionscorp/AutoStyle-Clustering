class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    words.reduce(Hash.new 0){|counts, w| counts.merge(w => counts[w] + 1) }
  end

  private
  def words
    @string.gsub(/[:!&@$%^.]/,'').downcase.split(/[\s,]+/)
  end
end
