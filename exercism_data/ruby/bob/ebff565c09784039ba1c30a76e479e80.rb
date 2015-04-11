class Sentence

  def initialize(content)
    @content = content
  end

  def upcase?
    @content.upcase == @content
  end

  def question?
    @content.end_with?("?")
  end

  def blank?
    @content.strip.empty?
  end

end

class Bob

  def hey(input)
    sentence = Sentence.new(input)
    if sentence.blank?
      'Fine. Be that way!'
    elsif sentence.upcase?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
