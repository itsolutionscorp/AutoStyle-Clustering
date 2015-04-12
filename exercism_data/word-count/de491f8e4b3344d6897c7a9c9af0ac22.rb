class Phrase

  def initialize(string)
    @words = string.gsub(/[^A-Za-z'0-9]/, ' ').split.map {|word| word.downcase }
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
