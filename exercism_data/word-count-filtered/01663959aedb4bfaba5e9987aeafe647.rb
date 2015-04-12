class Phrase

  def initialize(input)
    @input = input.downcase
  end

  def word_count
    word_count = Hash.new(0)

    @input.scan(/\w+/).each do |word|
      word_count[word] += 1
    end

    word_count
  end

end
