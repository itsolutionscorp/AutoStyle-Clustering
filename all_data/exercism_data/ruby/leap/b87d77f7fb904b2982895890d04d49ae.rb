class Year
  
  class << self
    
    def leap? year
      is_divisible_by_4 = divisible_by? year, 4
      is_divisible_by_100 = divisible_by? year, 100
      is_divisible_by_400 = divisible_by? year, 400

      is_leap = if is_divisible_by_4 && !is_divisible_by_100 && !is_divisible_by_400
        true
      elsif is_divisible_by_4 && is_divisible_by_100 && !is_divisible_by_400
        false
      elsif is_divisible_by_4 && is_divisible_by_100 && is_divisible_by_400
        true
      end

      is_leap
    end

    private 

    def divisible_by? year, number
      ( year % number ) == 0 ? true : false
    end

  end
  
end
