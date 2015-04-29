class Year
  def self.leap?(annum)
    return false if !evenly_divisible?(4, annum)
    if evenly_divisible?(100, annum)
      return false unless evenly_divisible?(400, annum)
    end
    true
  end
end

private

def evenly_divisible?(divisor, year)
  year % divisor == 0
end
