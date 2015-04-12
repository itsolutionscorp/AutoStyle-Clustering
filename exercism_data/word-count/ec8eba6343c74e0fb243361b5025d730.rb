class Phrase
  def initialize(text)
    @count = Hash.new(0)
    @text = text
    build_count
  end

  def word_count
    @count
  end

  def build_count
    @text.downcase.scan(/[\w']+/).each do |w|
      @count[w] += 1 unless w.empty?
    end
  end
end
