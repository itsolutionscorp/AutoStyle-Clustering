class Prime
  def self.nth(number)
    raise ArgumentError if number.zero?

    (number-1).times.with_object([2]) do |_, result|
      result << find_next_prime(result[-1].even? ? 1 : result[-1] )
    end.last
  end

  def self.find_next_prime(start)
    number = start + 2
    until is_prime?(number)
      number += 2
    end

    number
  end

  def self.is_prime?(number)
    # TODO: consider using a lazy sieve in the future
    root = Math.sqrt(number).round

    !((3..[root, 3].max) .to_a - [number])
    .select(&:odd?)
    .any? { |factor| number % factor == 0 }
  end
end
