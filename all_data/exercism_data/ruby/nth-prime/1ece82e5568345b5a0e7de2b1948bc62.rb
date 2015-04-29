class Prime
  Inf = 1.0 / 0.0
  
  def self.nth num
    raise ArgumentError if num == 0 
    (1..Inf).lazy_select { |v| check_prime? v }.take(num).last
  end

  def self.check_prime? num
    2.upto(Math.sqrt(num).to_i) { |n| return false if num % n == 0 }
    num == 1 ? false : true
  end

   private_class_method :check_prime?
end

module Enumerable
  def lazy_select
    Enumerator.new do |yielder|
      each do |obj|
        yielder.yield(obj) if yield(obj)
      end
    end
  end
end
