class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes(range = Array(2..@limit), index = 0)
    # prime_number = range[0]
    # #sudo code this shit
    # #take the first element in the range and then reject anything if it is a
    # #multiple of it.
    # # Find by modular division
    # # check each element.
    # #change position range[n + 1]
    # #when number is prime number don't reject
    # range.reject! { |number| number % prime_number == 0 && number != prime_number }
    # prime_number = range[1]
    # range.reject! { |number| number % prime_number == 0 && number != prime_number }

    # Solution #1: Pure Object Oriented Approach
    # Two defining characteristics of objects:
    # 1. State
    # 2. Behavior

    # range.each_with_index do |range_number, i|
    #   prime_number = range[i]
    #   range.reject! { |number| number % prime_number == 0 && number != prime_number }
    # end

    # Solution #2: Functional Programming Approach
    if index >= (range.length / 2) + 1
      range
    else
      new_range = range.reject { |number| number % range[index] == 0 && number != range[index] }
      primes(new_range, index + 1)
    end
  end
end
