require 'prime'

class Prime

  def nth(prime_count)
    if prime_count == 0
      raise ArgumentError, "Not an nth prime"
    else
      prime_list = Prime.first prime_count
      prime_list.last
    end
  end

end
