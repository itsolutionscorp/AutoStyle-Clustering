class Year
  def self.leap?(num)
    num % 4 == 0 and (not num % 100 == 0 or num % 400 == 0)
  end
end
