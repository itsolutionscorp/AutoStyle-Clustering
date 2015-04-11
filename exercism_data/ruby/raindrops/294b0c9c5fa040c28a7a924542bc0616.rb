class Array
  
  def delete_first_occurrence(item)
    delete_at(find_index(item) || length)
    self
  end

end

class Integer

  def included_in?(arr)
    arr.include?(self)
  end

end

class RaindropSound
  
  def initialize(num)
    @num = num
  end

  def reply
    return pling if @num == 3
    return plang if @num == 5
    return plong if @num == 7
  end

  def pling
    'Pling'
  end

  def plang
    'Plang'
  end

  def plong
    'Plong'
  end

end

class Raindrops

  require 'Prime'

  def convert(num)
    @original_number, @numbers = num, [num]
    @primes = Prime.first(100)
    factorialize
    build_sound_string
  end

  private

  def build_sound_string
    sound_string = ""
    if any_rain?(@numbers)
      @numbers.uniq.each do |num|
        sound_string = sound_string + RaindropSound.new(num).reply.to_s
      end
    else
      sound_string =@original_number.to_s
    end
    sound_string
  end

  def factor(num)
    result = []
    @primes.each do |prime|
      if num % prime == 0
        result << prime
        result << num/prime
        break
      end
    end
    result
  end

  def factorialize
    result = []
    @numbers.each do |num|
      if !num.prime?
        result += factor(num)
        @numbers.delete_first_occurrence(num)
        @numbers = @numbers + result
      end
    end
    factorialize unless all_prime?(@numbers)
    @numbers
  end

  def any_rain?(arr)
    result = false
    rain_drops = [3,5,7]
    arr.each do |num|
      if rain_drops.include?(num)
        result = true
        break
      end
    end
    result
  end

  def all_prime?(arr)
    result = true
    arr.each do |num|
      if !num.included_in? @primes
        result = false
        break
      end
    end
    result
  end


  # RETURNS STACK LEVEL TOO DEEP
  #
  # def all_prime?(arr)
  #   !arr.map { |num| @primes.select { |n| n == num }.empty? }.include?(false)
  # end


end
