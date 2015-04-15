class Year

  def self.leap?(date)
    date = date.year if date.respond_to?(:year)
    year = date.to_i

    year.modulo(400) == 0 || year.modulo(100) != 0 && year.modulo(4) == 0
  end

end
