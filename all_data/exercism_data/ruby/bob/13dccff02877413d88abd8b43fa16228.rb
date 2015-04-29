class Bob

  def hey(to_bob)
    to_bob = Ballyhoo.new(to_bob)
    case
    when to_bob.shouting?
      'Woah, chill out!'
    when to_bob.nosey?
      'Sure.'
    when to_bob.giving_silent_treatment?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Ballyhoo
  attr_reader :to_bob

  def initialize(to_bob)
    @to_bob = to_bob
  end

  def shouting?
    to_bob =~ /[A-Z+]/ && to_bob == to_bob.upcase
  end

  def nosey?
    to_bob.end_with?("?")
  end

  def giving_silent_treatment?
    to_bob.strip.empty?
  end
end
