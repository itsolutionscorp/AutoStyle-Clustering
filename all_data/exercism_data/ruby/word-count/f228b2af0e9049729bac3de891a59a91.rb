class Phrase
  attr_reader :words
  def initialize(input)
    @words = format_input(input)
  end

  def word_count
    words.each_with_object({}) do |word, counter|
      if counter[word]
        counter[word] += 1
      else
        counter[word] = 1
      end
    end
  end

  def format_input(input)
    input.downcase.scan(/\w+/)
  end

end
