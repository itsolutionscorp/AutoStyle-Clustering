class Phrase

  attr_reader :word_count

  def initialize(string)
    @word_count = Hash.new
    string.downcase.scan(/[[:alnum:]]+/).each do | word | 
      @word_count.key?(word) ? @word_count[word] += 1 : @word_count[word] = 1
    end
  end

end
