class SumOfMultiples
  # Initialize with the splat, which allows a variable
  # number of arguments to be passed in and treated
  # as an array
  def initialize(*args)
    @args = args
  end

  # Class method
  # This is a helper method to find all multiples of a
  # given integer that are less than a given upper_limit
  # and return them as an array.
  def self.find_multiples(upper_limit, integer)
    multiples_array = []

    n = 1
    while n * integer < upper_limit
      multiples_array << n * integer
      n += 1
    end

    multiples_array
  end

  # Class method
  def self.create_all_multiples_array(upper_limit, args_array)
    all_multiples = []

    args_array.each do | arg |
      all_multiples += find_multiples(upper_limit, arg)
    end

    # Return all the multiples found for each number but only
    # one of each.
    all_multiples.uniq
  end

  # Class method
  # This class method self.to handles all the logic for doing the
  # calculation. If no array of numbers is passed in, then it uses
  # [3,5] as the default.
  def self.to(upper_limit, args_array=[3,5])
    all_multiples = create_all_multiples_array(upper_limit, args_array)

    if all_multiples.length == 0
      sum = 0
    else
      sum = all_multiples.inject{ |sum, x| sum += x }
    end

    sum
  end

  # Instance method
  # This instance method simply passes along the arguments
  # used when creating the object to the class method
  # self.to which then runs the same calculation.
  def to(upper_limit)
    SumOfMultiples.to(upper_limit, @args)
  end
end
