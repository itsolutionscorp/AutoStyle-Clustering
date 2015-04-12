class Phrase
  CHAR_MASK = /[^[a-z][0-9]']/

  def initialize(string)
    @words = string.downcase.gsub(CHAR_MASK, ' ').split
  end

  def word_count
    Hash.new(0).tap do |counts|
      @words.each { |word| counts[word] += 1 }
    end
  end
end
