class Phrase

  def initialize(string)
    @words = string.downcase.gsub(/[^A-Za-z'0-9]/, ' ').split
  end

  def count
    @counts = Hash.new(0)
    @words.each do |word|
      @counts[word] += 1
    end
    @counts
  end

  def word_count
    @counts ||= count
  end

end
