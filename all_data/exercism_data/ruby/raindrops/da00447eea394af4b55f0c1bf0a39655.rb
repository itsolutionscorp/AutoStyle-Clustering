class Raindrops
  def self.convert number
    result = ''
    if divisible_by_3_5_or_7?(number)
      result << "Pling" if divisible_by_3?(number)
      result << "Plang" if divisible_by_5?(number)
      result << "Plong" if divisible_by_7?(number)
    else
      result << number.to_s
    end
    result
  end

  private

    def self.divisible_by_3_5_or_7? number
      divisible_by_3?(number) || divisible_by_5?(number) || divisible_by_7?(number)
    end

    def self.divisible_by_3? number
      number % 3 == 0
    end

    def self.divisible_by_5? number
      number % 5 == 0
    end

    def self.divisible_by_7? number
      number % 7 == 0
    end

end
