require 'prime'
class Prime
  def nth(n)
    if n > 0
      Prime::EratosthenesSieve.instance.get_nth_prime(n-1)
    else
      raise ArgumentError
    end
  end
end
