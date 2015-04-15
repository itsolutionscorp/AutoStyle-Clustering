class Phrase
  attr_accessor :text
  def initialize(text)
    @text = text
  end

  def word_count
    ans = Hash.new(0)
    text.gsub(/[^0-9A-Za-z ']/, ' ').downcase.split(' ').each do |word|
      ans[word] += 1
    end
    ans
  end
end
