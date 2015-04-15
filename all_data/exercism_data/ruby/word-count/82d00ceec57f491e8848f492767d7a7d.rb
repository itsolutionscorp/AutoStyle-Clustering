class Phrase
  attr_reader :word_count, :input_string

  def initialize(input_string)
    @input_string = input_string
    @word_count = Hash.new(0)
    calculate_word_count
  end

  def get_count_for(word)
    word_count[word]
  end

  private 
    def calculate_word_count
      @input_string.downcase.scan(/[\w']+/).each { |word| @word_count[word] += 1 }
    end
end
