class Bob
  def hey(prompt)

    adult_speak = Prompt.new prompt

    if adult_speak.boring?
      'Fine. Be that way.'
    elsif adult_speak.yelling?
      'Woah, chill out!'
    elsif adult_speak.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Prompt
  def initialize(prompt)
    @prompt = prompt.to_s
  end

  def yelling?
    @prompt == @prompt.upcase
  end

  def question?
    @prompt.end_with? '?'
  end

  def boring?
    @prompt.empty?
  end
end
