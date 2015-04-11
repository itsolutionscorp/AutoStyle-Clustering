class Sieve
  def initialize(num)
    @num = num
  end
  attr_reader :num

  def primes
    values = {}
    (2..num).each do |x|
      values[x] = true
    end
    i = (2..num).to_a
    ans = []
    values.each_key do |x|
      if values[x] = true
         i.each do |y|
          if x % y == 0 && x / y != 1
            values[x] = false
          end
        end
      end
    end
    ans = values.collect {|key, value| key if value == true}
    ans.compact
  end
end
