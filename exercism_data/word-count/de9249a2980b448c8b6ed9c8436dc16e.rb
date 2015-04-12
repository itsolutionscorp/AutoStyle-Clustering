class Phrase

  attr_reader :word_count

  def initialize(string)
    @word_count = Hash.new 0
    string.downcase.scan(/['\w]+/) do |word|
      @word_count[word] += 1
    end
  end

end
