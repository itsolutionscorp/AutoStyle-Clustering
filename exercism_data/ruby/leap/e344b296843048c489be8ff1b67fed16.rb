class Year
  
  class << self
    
    def leap? year
      is_divisible_by_4 = divisible_by? year, 4
      is_divisible_by_100 = divisible_by? year, 100
      is_divisible_by_400 = divisible_by? year, 400

      is_leap = if is_divisible_by_4 and is_divisible_by_100.==(false) or is_divisible_by_400
        true
      end

      is_leap
    end

    private 

    def divisible_by? year, number
      year.%(number).zero?
    end

  end

end
