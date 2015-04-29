class Phrase
  CHAR_MASK = /[^[a-z][0-9]']/m

  def initialize(string)
    @string = clean(string)
  end

  def word_count
    words = @string.split

    Hash.new(0).tap do |counts|
      words.each { |word| counts[word] += 1 }
    end
  end

  private

  def clean(string)
    string.downcase.gsub(CHAR_MASK, ' ')
  end
end
