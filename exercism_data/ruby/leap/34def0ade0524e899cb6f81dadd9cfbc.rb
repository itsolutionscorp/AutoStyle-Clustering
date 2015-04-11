class Year
  class << self
    def leap?(year)
      if divisible_by_400?(year)
        true
      elsif divisible_by_100?(year)
        false          
      elsif divisible_by_4?(year)
        true
      else
        false
      end
    end

    private
    def divisible_by_400?(year)
      if year % 400 == 0
        puts "#{year} is leap year because it is divisible by 400"
        true
      end
    end

    def divisible_by_100?(year)
      if year % 100 == 0
        puts "#{year} is not leap year because it is divisible by 100"
        true
      end
    end

    def divisible_by_4?(year)
      if year % 4 == 0
        puts "#{year} is leap year because it is divisible by 4"
        true
      end
    end
  end
end
