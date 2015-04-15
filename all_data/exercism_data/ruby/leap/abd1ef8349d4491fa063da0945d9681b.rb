class Year
  def self.leap?(year)

    if divisible_by?(year, 4)
      if divisible_by?(year, 100)
        divisible_by?(year, 400) ? (return true) : (return false)
      else
        return true
      end
      return false
    end
  end

private

  def self.divisible_by?(year, number)
    year%number==0 ? true : false
  end
end
