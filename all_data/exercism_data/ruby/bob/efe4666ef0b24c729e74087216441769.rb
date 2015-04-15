module BobsResponses
  def fine
    "Fine. Be that way!"
  end

  def chill_out
    "Whoa, chill out!"
  end

  def sure
    "Sure."
  end

  def whatever
    "Whatever."
  end
end

module MessageChecks
  def not_saying_anything?(x)
    x.strip.empty?
  end

  def yelling?(x)
    x.upcase == x && x =~ /[A-Z]/
  end

  def question?(x)
    x.end_with?('?')
  end

  def default?(x)
    true
  end
end

class BobsBehavior
  include MessageChecks
  include BobsResponses

  BEHAVIORS = {
    :not_saying_anything? => :fine,
    :yelling?             => :chill_out,
    :question?            => :sure,
    :default?             => :whatever,
  }

  def match(input)
    BEHAVIORS.each do |match, answer|
      return self.send(answer) if self.send(match, input)
    end
  end
end

class Bob
  attr_accessor :behavior

  def initialize
    @behavior = BobsBehavior.new
  end

  def hey(input)
    behavior.match(input)
  end
end
