class Year

  def self.leap? year
    is_leap = (year % 4).zero?
    if (year % 100).zero?
      is_leap = false unless (year % 400).zero?
    end
    is_leap
  end

end
