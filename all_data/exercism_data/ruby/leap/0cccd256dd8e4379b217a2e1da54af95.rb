class Year

  def self.leap?(year_to_check)
    (year_to_check % 4).zero? && ! (year_to_check % 100).zero? || (year_to_check % 400).zero?
  end

end
