class Words
  attr_reader :sentence

  def initialize(input)
    @sentence = input.downcase.gsub(/[^a-z0-9 ]/,"").split(" ")
  end

  def count
    answer = Hash.new(0)
    sentence.each do |word|
      answer[word] += 1
    end
    answer
  end
end
