class Phrase
  def initialize(string)
    @string = string
    @counts = Hash.new(0)
  end

  def word_count
    return @counts unless @counts.empty?
    @string.split(/[\s,]/).each do |word|
      word = normalize(word)
      next if word.empty?
      @counts[word] += 1
    end
    @counts
  end

  private

  def normalize(word)
    word.downcase.gsub(/[^[:alnum:]']/, "")
  end
end
