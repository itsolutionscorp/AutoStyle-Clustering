class Bob
  def hey(text)
    randomdude = Interlocutor.new(text.to_s)
    if randomdude.is_silent?
      'Fine. Be that way.'
    elsif randomdude.is_yelling?
      'Woah, chill out!'
    elsif randomdude.is_asking_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Interlocutor < String
  def initialize(input)
    @input = input
  end

  def is_yelling?
    @input == @input.upcase
  end
  
  def is_asking_a_question?
    @input.end_with?('?')
  end
  
  def is_silent?
    @input.empty?
  end
end
