class Phrase
  def initialize(input)
    @input = input
  end
  def word_count
    @input = @input.gsub(/[^a-zA-Z\s\d']/, ' ').downcase.split
    counts = Hash.new(0)
      @input.each do |word|
      counts[word] += 1
      end
    @input = @input.to_s
    counts
  end
end
