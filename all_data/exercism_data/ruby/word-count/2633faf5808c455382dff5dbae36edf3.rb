class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    count = {}
    splitted = input.downcase.scan(/[\w']+/)
    splitted.each do | word |
      if !count.key?(word)
        count[word] = 1
      else
        count[word] = count[word] + 1
      end
    end
    count
  end

end
