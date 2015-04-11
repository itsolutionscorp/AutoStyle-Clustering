class Sentence
  attr_accessor :message

  def initialize(message = "")
    @message = message
  end

  def compared_with(sentence)
    letters.first(sentence.length).zip(sentence.letters)
  end

  def letters
    message.split ""
  end

  def length
    message.length
  end
end

class Hamming
  class << self
    def compute(x, y)
      sentence1 = Sentence.new x
      sentence2 = Sentence.new y

      sentence1
        .compared_with(sentence2)
        .reject {|a, b| a == b }
        .count
    end
  end
end
