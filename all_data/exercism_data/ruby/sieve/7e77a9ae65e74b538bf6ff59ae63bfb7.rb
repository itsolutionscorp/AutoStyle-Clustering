class Sieve

  def initialize(limit)
    @data = {}
    @limit = limit
  end

  def reset_data
    range.each { |n| @data[n] = :prime }
  end

  def fill_data
    range.each do |current|
      @data.each do |key, value|
        @data[key] = :not_prime if key > current && key % current == 0
      end
    end
  end

  def range
    (2..@limit)
  end

  def primes
    reset_data && fill_data
    @data.select do |key, value|
      value == :prime
    end.keys
  end
end
