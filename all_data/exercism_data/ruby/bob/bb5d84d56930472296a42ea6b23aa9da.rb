module Response
  def answer
    "Sure."
  end

  def chill
    "Whoa, chill out!"
  end

  def nothing
    "Fine. Be that way!"
  end

  def whatever
    "Whatever."
  end
end

class Greeting

  def initialize(greeting)
    @greeting = greeting
  end

  def question?
    @greeting.end_with? '?'
  end


  def yelling?
    @greeting == @greeting.upcase && @greeting =~ /[A-Z]/
  end

  def silent?
    @greeting.lstrip.empty?
  end

end

class Bob
  include Response

  def hey(greeting)
    g = Greeting.new(greeting)
    case
      when g.yelling? then chill
      when g.question? then answer
      when g.silent? then nothing 
      else whatever
    end
  end
  
end
