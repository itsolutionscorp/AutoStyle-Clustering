class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schd)
    day_sym = (weekday.to_s + '?').to_sym
    if schd == :teenth
      find_date(day_sym, 13, 20)
    elsif schd == :first
      find_date(day_sym, 1, 8)
    elsif schd == :second
      find_date(day_sym, 8, 15)
    elsif schd == :third
      find_date(day_sym, 15, 22)
    elsif schd == :fourth
      find_date(day_sym, 22, 31)
    elsif schd == :last
      find_date(day_sym, 22, 31, true)
    end
  end


  def find_date(weekday, start, last, rev = false)
    range = rev ? (start..last).to_a.reverse : (start..last).to_a

    range.each do |i|
      if Date.valid_date?(@year, @month,i) && Date.new(@year, @month, i).send(weekday)
        return  Date.new(@year, @month, i)
      end
    end
  end

end
