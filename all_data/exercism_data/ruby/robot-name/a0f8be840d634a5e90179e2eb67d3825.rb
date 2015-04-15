# # Robot Name

# Write a program that manages robot factory settings.

# When robots come off the factory floor, they have no name.

# The first time you boot them up, a random name is generated, such as
# RX837 or BC811.

# Every once in a while we need to reset a robot to its factory settings,
# which means that their name gets wiped. The next time you ask, it gets a
# new name.

# Random names means a risk of collisions. In some exercism language
# tracks there are tests to ensure that the same name is never used twice.

class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def generate_name
    letters = [*("A".."Z")]
    numbers = [*("0".."9")]

    letter_name = letters.sample(2).join
    num_name    = numbers.sample(3).join
    letter_name + num_name
  end

  def reset
    @name = generate_name
  end

end
