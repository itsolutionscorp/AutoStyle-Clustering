require 'singleton'
require 'pry'

class Robot

  def name
    @name ||= Names.instance.new_name
  end

  def reset
    @name = nil
  end

end

class Names
  include Singleton

  PREFIXES = ("AA".."ZZ").to_a
  POSTFIXES = ("000".."999").to_a

  def new_name
    "#{prefix}#{postfix}"
  end

  private

  def prefix
    PREFIXES[rand(PREFIXES.length)]
  end

  def postfix
    POSTFIXES[rand(POSTFIXES.length)]
  end

end

# I feel (slightly less) dirty
#
# Is there something in the readme that the test suite is missing here?  This
# passes every time I run the tests, but only because of the sheer number of
# possible combinations of names 'essentailly' aaures that you don't get a
# repeat name.  The way I read it, each provided name was supposed to be unique
# and once used, could not be used again.  Is that a misunderstanding?
