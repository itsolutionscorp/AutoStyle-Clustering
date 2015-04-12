class Phrase

  attr_reader :word_count

  def initialize(words)
    @word_count = Hash.new(0)
    words.downcase.gsub(/[^[:alnum:]]/, ' ').split.each do |w|
      @word_count[w] = @word_count[w] + 1 
    end
  end

end
