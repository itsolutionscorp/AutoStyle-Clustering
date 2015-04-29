require 'prime'
class Prime

  def nth(n)
    raise ArgumentError if n < 1
    count = 1
    index = 2
    until count == n
      index += 1
      count += 1 if (2..index **0.5).none? { |num| index % num == 0 }
    end
    index
  end

end
