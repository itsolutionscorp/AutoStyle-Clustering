class Phrase

  def initialize text
    @text = text
  end

  def word_count
    return {} if @text.nil?
    counts = Hash.new 0
    @text.downcase.scan(/[\w']+/).each {|w| counts[w] += 1 }
    counts
  end

end
