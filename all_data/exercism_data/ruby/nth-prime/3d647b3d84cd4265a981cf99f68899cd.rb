#require 'prime'
class Prime
  def self.nth(num)
    fail ArgumentError, 'What does a non positive positioned prime even mean?' if num < 1
    found = [2]
    val = 2
    while num > found.length
      val += 1
      #found << val if prime?(val)
      prime = true
      found.each do |i|
        if val % i == 0
          prime = false
          break
        end
      end
      found << val if prime
    end
    found.last
  end

  def self.nth_sieve(num)
    fail ArgumentError, 'What does a non positive positioned prime even mean?' if num < 1
    max = num*(Math.log(num) + Math.log(Math.log(num)))
    max = max < 0 ? 2 : max.to_i + 3
    vals = (2..max).to_a
    prime = false
    num.times do |i|
      prime = vals.shift
      vals.select! { |x| (x % prime != 0) || (x < prime**2) }
    end
    prime
  end
end
