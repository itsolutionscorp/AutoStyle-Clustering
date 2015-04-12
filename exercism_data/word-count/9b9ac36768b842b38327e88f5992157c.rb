class Phrase

  def initialize(str)
    @str = str
  end

  def word_count
    counts = Hash.new(0)
    each_word { |w| counts[w] += 1 }
    counts
  end

  private
  def each_word(&block)
    @str.downcase.scan(/\w+/, &block)
  end

end
