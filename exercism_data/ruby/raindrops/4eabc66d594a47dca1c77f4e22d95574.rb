require 'prime'

class Raindrops
  @raindrop_conversion_hash = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(num)
    prime_factorization(num)
  end

  def self.prime_factorization(num)
    starting_num = num
    prime_factorization_array = []
    if num <= 1
      prime_factorization_array.push(num)
    end
    Prime.each(10) do |prime_num|
      while num % prime_num == 0
        prime_factorization_array.push(prime_num)
        num = num / prime_num
      end
    end
    word_insertion(prime_factorization_array, starting_num)
  end

  def self.word_insertion(array, num)
    raindrop_string = ""
    array.uniq.each do |prime_factor|
      @raindrop_conversion_hash.each do |k,v|
        if prime_factor == k
          raindrop_string << v
        end
      end
    end
    if raindrop_string.size == 0
      return num.to_s
    else
      return raindrop_string
    end
  end

end

Raindrops.convert(52)
