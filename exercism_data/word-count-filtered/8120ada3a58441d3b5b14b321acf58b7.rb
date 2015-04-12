class Phrase
  def initialize(text)
    @count = Hash.new(0)

    text.scan(/\w+/).each do |word|
      @count[word.downcase] += 1
    end
  end

  def word_count
    @count
  end
end
