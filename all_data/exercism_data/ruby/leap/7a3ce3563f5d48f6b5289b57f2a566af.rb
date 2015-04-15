class Year

  @@Denominators = [4, 100, 400]

  def self.leap?(year_to_check)
    divisble_by(year_to_check, @@Denominators[0]) \
      && (not (divisble_by(year_to_check, @@Denominators[1])) \
        && (not (divisble_by(year_to_check, @@Denominators[2]))))
  end

  private

  def self.divisble_by(year_to_check, amount)
    return true if (year_to_check % amount).zero?
    false
  end

end
