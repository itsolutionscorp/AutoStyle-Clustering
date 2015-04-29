class Year
  def self.leap?(year)
    if year % 4 == 0
      answer = true
      if year % 100 == 0
        answer = false unless year % 400 == 0
      end
      answer
    end
  end
end
