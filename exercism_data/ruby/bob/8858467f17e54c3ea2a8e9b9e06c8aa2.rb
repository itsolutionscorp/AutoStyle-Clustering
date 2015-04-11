class Bob
  def hey(input)
    input = Sentence.new(input)

    if input.empty?
      'Fine. Be that way.'
    elsif input.yelling?
      'Woah, chill out!'
    elsif input.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Sentence
  attr_reader :content

  def initialize(str)
    @content = str.to_s
  end

  def empty?
    content.empty?
  end

  def yelling?
    content == content.upcase
  end

  def question?
    content.end_with? '?'
  end
end
