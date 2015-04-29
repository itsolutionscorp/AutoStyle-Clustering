class Year

  def self.leap?(year)
    if year % 400 == 0
      'Yes, ' + year.to_s + ' is a leap year'
    elsif year % 4 == 0 && year % 100 != 0
      'Yes, ' + year.to_s + ' is a leap year'
    else
      puts 'No, ' + year.to_s + ' is not a leap year'
    end
  end

end
