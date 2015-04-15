class Proverb
  attr_reader :stuff, :qualifier

  def initialize(*stuff)
    @qualifier = stuff.last.instance_of?(Hash) ? stuff.pop[:qualifier] + ' ' : ''
    @stuff = stuff
  end

  def to_s
    (0...stuff.length - 1).reduce('') do |proverb, i|
      proverb + for_want(stuff[i], stuff[i + 1])
    end + all(stuff.first)
  end

  def for_want(thing1, thing2)
    "For want of a #{thing1} the #{thing2} was lost.\n"
  end

  def all(thing)
    "And all for the want of a #{qualifier}#{thing}."
  end
end
