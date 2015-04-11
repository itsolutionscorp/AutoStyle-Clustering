class Bob
  def hey(greeting)
    greeting = Greeting.new(greeting)

    case
      when greeting.muting?
        'Fine. Be that way!'
      when greeting.yelling?
        'Woah, chill out!'
      when greeting.question?
        'Sure.'
      else
        'Whatever.'
    end
  end
end

class Greeting
  def initialize(greeting)
    @greeting = greeting
  end

  def muting?
    @greeting.strip.empty?
  end

  def yelling?
    @greeting == @greeting.upcase
  end

  def question?
    @greeting.end_with? '?'
  end
end
