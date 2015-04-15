class Year
  def self.leap?(num)
    # n % 4 == 0 && (n % 400 == 0 || n % 100 != 0)
    divisible_by_4 = num % 4 == 0
    divisible_by_100 = num % 100 == 0
    divisible_by_400 = num % 400 == 0

    if divisible_by_4
      is_leap = true
      if divisible_by_100
        is_leap = false
        if divisible_by_400
          is_leap = true
        end
      end
    end

    is_leap
  end
end
