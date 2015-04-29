class Bob

  def hey(text)

    sentence = Sentence.new(text)

    if sentence.silent?
      'Fine. Be that way.'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.asking?
      'Sure.'
    else
      'Whatever.'
    end

  end

end


class Sentence

  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def silent?
    text.empty?
  end

  def yelling?
    text.upcase == text
  end

  def asking?
    text.end_with?('?')
  end

end
