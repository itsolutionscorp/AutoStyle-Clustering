class Sieve

  def initialize(limit)
    @number_elements = 2.upto(limit).map do |number|
      {
          number: number,
          composite?: nil
      }
    end
    @primes = nil
  end

  def primes
    @primes.nil? ? calculate_primes : @primes
  end

  private

  def calculate_primes
    prime_elements = @number_elements.select do |number_element|
      is_prime_element?(number_element)
    end

    prime_elements.map {|element| element[:number] }
  end

  def is_prime_element?(number_element)
    number = number_element[:number]

    # Note: I don't particularly like that this returns an implied truthiness.
    # While a nil result of this method is interpreted as false, the
    # truthiness is completely dependent on the implementation of mark_composite.
    #
    # I'd say that's leaking the implementation specifics out of mark_composite
    # that should remain private to that method. But, how does one know what
    # the return value should be in the first place without looking at the
    # implementation of both this method and mark_composite?
    #
    # The following looks verbose, but is explicit about the return value and
    # does not depend on the implementation of the mark_composites method.
    #
    # if number == 2 || ! number_element[:composite?]
    #   mark_composites(number)
    #   true
    # elsif
    #   false
    # end

    if number == 2 || ! number_element[:composite?]
      mark_composites(number)
    end
  end

  def mark_composites(number)
    @number_elements.each do |number_element|
      if number_element[:number] % number == 0
        number_element[:composite?] = true
      end
    end
  end
end
