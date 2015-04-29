# robot.rb

class Robot
  def initialize(name=nil)
    @name = name
  end

  def name
    @name ||= random_name
  end

  def reset
    @name = nil
  end

  def random_name
    random_chars(2) + random_numbers(3)
  end

  # The elements are chosen by using random
  # and unique indices into the array in order
  # to ensure that an element doesn’t repeat
  # itself unless the array already contained
  # duplicate elements.
  #  => if you want duplicate elements you have
  # to duplicate the array
  def random_chars(size)
    [*('A'..'Z')].sample(size).join
  end

  def random_numbers(size)
    [*('0'..'9')].sample(size).join
  end
end
