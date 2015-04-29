class Prime
  include Enumerable

  def self.nth(index)
    raise ArgumentError, 'index out of bound' if index <= 0
    new.take(index).last
  end

  def each(&block)
    (2..Float::INFINITY).each do |i|
      yield(i) if prime?(i)
    end
  end

  def prime?(i)
    (2..Math.sqrt(i).to_i).none? { |divisor| i % divisor == 0 }
  end
end
