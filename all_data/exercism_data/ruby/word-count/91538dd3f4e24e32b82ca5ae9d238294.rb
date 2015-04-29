class Phrase
  def initialize(text)
    words = text.downcase.gsub(/[^0-9a-z, ]/, '').squeeze(' ').gsub(/,[ ]*/, ',').split(/[ ,]/)
    @counts = words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  def word_count
    @counts
  end
end
