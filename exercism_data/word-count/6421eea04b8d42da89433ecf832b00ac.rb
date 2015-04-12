class Phrase
  attr_reader :word_count

  def initialize(phrase)
    count(phrase)
  end

  def count(phrase)
    @word_count = {}

    phrase.downcase.gsub(/[!@#$%^&*()\.,:]/,' ').split(' ').each do |word|
      @word_count[word] = @word_count[word].nil? ? 1 : @word_count[word] + 1
    end
  end
end
