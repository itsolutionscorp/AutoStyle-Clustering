class Phrase
  def initialize(input)
    @words = clean(input).split(/[\s,]+/)
  end

  def word_count
    count = Hash.new(0)
    @words.each { |word| count[word] += 1 }
    count
  end

  private

  def clean(string)
    string.downcase.gsub(/[^\w\s',]+/, '')
  end
end
