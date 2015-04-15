class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @word_count = {}
    phrase.downcase.gsub(/[^0-9a-z']/,' ').split.each do |word|
      if @word_count.has_key? (word)
        @word_count[word] += 1
      else
        @word_count[word] = 1
      end
    end

  end
end
