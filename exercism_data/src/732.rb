class Phrase
  def initialize(phrase)
    @count = {}
    phrase = phrase.downcase.gsub(/[^a-z'0-9\s]/i, ' ')
    phrase.split(' ').each do |word|
      if @count[word].nil?
        @count[word] = 1
      else
        @count[word] = @count[word] + 1
      end
    end
  end

  def word_count
    @count
  end

end
