class Year
  class << self
    
    def leap? year
      raise ArgumentError, 'year must be an integer' unless year.is_a? Integer
      return true if year % 400 == 0
      return true if year % 4 == 0 and year % 100 != 0
      false
    end

  end
end
