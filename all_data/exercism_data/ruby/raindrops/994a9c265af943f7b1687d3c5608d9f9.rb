require 'pry'
class Raindrops
  def self.convert(num)
    factors = prime_factors(num)
    convert_to_raindrops(factors, num)
  end

  private

  def self.convert_to_raindrops(factors, num)
    string = factors.map do |factor|
      case factor
      when 3
        'Pling'
      when 5
        'Plang'
      when 7
        'Plong'
      end
    end.uniq.join('')

    string.empty? ? num.to_s : string
  end


  def self.prime_factors(num)
    p_factors = []
    get_factors(num).each do |factor|
      i_factors = [factor]
      while(i_factors.size > 0) do
        p_factors += i_factors.select { |f| is_prime?(f) }
        i_factors.delete_if { |f| is_prime?(f) }

        i_factors = i_factors.map { |f| get_factors(f) }.flatten
      end
    end
    p_factors
  end

  private
  def self.get_factors(num)
    factors = []
    (2..(num.to_f/2).floor).each do |i|
      factors << i if is_factor?(num, i)
    end
    factors << num if factors.empty?
    factors
  end

  def self.is_prime?(num)
    i = 2
    while (i < (num/2).floor)
      return false if is_factor?(num, i)
      i += 1
    end
    true
  end

  def self.is_factor?(num, divisor)
    (num % divisor) == 0
  end
end
