class Sieve
  
  attr_reader :limit
  private :limit

  def initialize(limit)
    @limit = limit
  end

  def primes
    @primes ||= calculate_primes
  end

  private

    def calculate_primes
      numbers = []
      range = (2..limit).to_a
      range.each do |current_number|
        numbers << current_number
        range.reject! { |number| number % current_number == 0 and number > current_number  }
      end
      numbers
    end

end
