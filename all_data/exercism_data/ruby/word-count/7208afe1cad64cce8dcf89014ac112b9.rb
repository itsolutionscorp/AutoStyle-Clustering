class Phrase
  def initialize(string)
    @string = string.downcase.gsub(/[^a-z'\d\s]/, ' ').split
  end
  def word_count
    @counts = {}
    @string.each do |word|
      @counts[word].nil? ? @counts[word] = 1 : @counts[word] += 1
    end
    @counts
  end
end
