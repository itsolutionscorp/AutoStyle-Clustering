class Sieve
  def initialize(num)
    @num = num
  end

  def primes
    range_array = (2..@num).to_a
    result = []
    range_array.each do |x|
      result << x
      range = range_array[range_array.index(x)+1..-1]
      range.delete_if { |z| z % x != 0 }
      range_array.delete_if { |q| range.include?(q) }
    end

    return result
  end

end
