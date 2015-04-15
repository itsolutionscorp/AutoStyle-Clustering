class Year
  def self.leap?(year_given)
    case
      when check(year_given, 400)
        true
      when check(year_given, 100)
        false
      when check(year_given, 4)
        true
      else
        false
    end
  end

  def self.check(year_given, div_by)
    year_given % div_by == 0
  end

end
