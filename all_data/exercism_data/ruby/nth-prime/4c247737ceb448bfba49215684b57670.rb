class Prime

  def self.nth target
    if target == 0
      raise ArgumentError
    elsif target == 1
      return 2
    end
 
    cur_num = 3
    index = 1
   
    while true
      index += 1 if is_prime?(cur_num)
      return cur_num if index == target
      cur_num += 2
    end
  end

  def self.is_prime?(n)
    ((2..(Math.sqrt(n)))).each do |i|
      return false if n % i == 0
    end
    return true
  end
end
