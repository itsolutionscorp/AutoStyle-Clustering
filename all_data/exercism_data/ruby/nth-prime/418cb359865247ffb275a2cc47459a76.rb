class Prime
  def self.nth(num)
    raise ArgumentError if num < 1
    loop do
      if @@prime_list.count >= num
        return @@prime_list[num-1]
      else
        @@prime_list << Prime.next_prime
      end
    end
  end

  private
    @@prime_list = [2]

    def self.next_prime
      num = @@prime_list.last + 1
      loop do
        return num if num.is_prime?
        num += 1
      end
    end
end

class Integer
  def is_prime?
    (2...self).each { |x| return false if self % x == 0 }
    true
  end
end
