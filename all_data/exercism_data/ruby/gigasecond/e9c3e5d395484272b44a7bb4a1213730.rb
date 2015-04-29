class Gigasecond

  def self.from(birth_date)
    if birth_date.is_a?(Date)
      birth_date + 11574
    elsif birth_date.respond_to?(:to_date)
      birth_date.to_date + 11575
    else
      raise ArgumentError, "argument: #{birth_date} is not valid"
    end
  end

end
