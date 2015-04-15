class Sieve
  def initialize(number)
    @number = number
    @sieve = Array.new(number+1)

    for ele in (0..@number) do
      @sieve[ele] = true
    end
    @sieve[0] = false
    @sieve[1] = false
  end

  def primes
    do_sieve
    format_results
  end

  def do_sieve
    for i in (2..@number) do
      if is_prime?(i)
        mark_off_divisors_of_num(i)
      end
    end
  end

  private
  def mark_off_divisors_of_num(number)
    for j in (2..@number) do
      @sieve[number*j] = false
    end
  end

  def is_prime?(number)
    @sieve[number]
  end
  def format_results
    results = []
    for i in (0..@number) do
      if @sieve[i]
        results << i
      end
    end
    results
  end
end
