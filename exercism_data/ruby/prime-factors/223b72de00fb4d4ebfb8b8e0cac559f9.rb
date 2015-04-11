class PrimeFactors
  def self.for(first_number)
    res_array = []
    max_number = first_number

    res = self.iteration(first_number)
 

    res_array
    
  end
  def self.iteration(first_number)
    temp_ary = Array.new(first_number) { |num| num+1 }.first  { |x| first_number % x == 0 }
    p temp_ary
    new_num = temp_ary.reduce { |first_number, n| first_number % n }

    return
  end
end
