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
    @text.split
    temp = @text.split(/\w(?=.*\w)^(\w|')+$/i)
    temp.join.split(/\W/).each do |w|
      @count[w.downcase] += 1 unless w.empty?
      @count
    end
  end
end
