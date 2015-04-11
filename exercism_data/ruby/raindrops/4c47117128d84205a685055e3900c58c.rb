class Raindrops
  
  def self.convert(num)
     result = self.translate(self.find_prime_factors(num))
     result.empty? ? num.to_s : result
  end
  
  def self.translate(prime_list)
    translation = ""
    translation << "Pling" if prime_list.include? 3
    translation << "Plang" if prime_list.include? 5
    translation << "Plong" if prime_list.include? 7
    translation
  end

  def self.find_prime_factors(num)
    orig_num = num
    if num < 2
      list = [1]
      return list
    end
    list = []

    while num % 2 == 0 do
      list << 2
      num /= 2
    end
    i = 3
    while i < num
      while num % i == 0
        list << i
        num /= i
      end
      i += 2
    end
    list
  end
      

end 
