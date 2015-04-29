class Bob

  def hey(to_bob)
    to_bob = Communicate.new(to_bob)
    case
    when to_bob.im_shouting?
      'Woah, chill out!'
    when to_bob.im_nosey?
      'Sure.'
    when to_bob.im_giving_silent_treatment?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Communicate
  attr_reader :to_bob

  def initialize(to_bob)
    @to_bob = to_bob
  end

  def im_shouting?
    to_bob =~ /[A-Z+]/ && to_bob == to_bob.upcase
  end

  def im_nosey?
    to_bob.end_with?("?")
  end

  def im_giving_silent_treatment?
    to_bob.strip.empty?
  end
end
