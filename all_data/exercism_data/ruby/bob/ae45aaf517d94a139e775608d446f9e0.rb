module Verbal
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

class Bob
  include Verbal

  def hey(greeting)
    @greeting = greeting
    case
      when yelling? then chill
      when question? then answer
      when silent? then nothing
      else whatever
    end
  end

  private

  def question?
    @greeting[-1] == '?'
  end


  def yelling?
    @greeting == @greeting.upcase && @greeting =~ /[A-Z]/
  end

  def silent?
    @greeting.strip.empty?
  end
end