class Bob
  def hey(text)
    random_dude = Interlocutor.new(text)
    if random_dude.silent?
      'Fine. Be that way.'
    elsif random_dude.yelling?
      'Woah, chill out!'
    elsif random_dude.asking_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Interlocutor 
  def initialize(input)
    @input = input.to_s
  end

  def yelling?
    @input == @input.upcase
  end
  
  def asking_a_question?
    @input.end_with?('?')
  end
  
  def silent?
    @input.empty?
  end
end
