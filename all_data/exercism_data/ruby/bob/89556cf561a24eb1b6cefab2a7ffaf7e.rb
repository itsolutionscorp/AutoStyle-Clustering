class Bob

  def hey(tip_off)
    shout_at_bob = ShoutAtBob.new(tip_off)
    case shout_at_bob
    when is(:quiet?)   then "Fine. Be that way!"
    when is(:yelling?) then "Woah, chill out!"
    when is(:asking?)  then "Sure."
    else "Whatever."
    end
  end

  private

  def is(activity)
    activity.to_proc
  end

end

class ShoutAtBob 
  attr_reader :tip_off

  def initialize(tip_off)
    @tip_off = tip_off
  end

  def yelling?
    tip_off == tip_off.upcase  
  end

  def asking?
    tip_off.end_with?("?")
  end

  def quiet?
    tip_off.strip.empty?
  end
end
