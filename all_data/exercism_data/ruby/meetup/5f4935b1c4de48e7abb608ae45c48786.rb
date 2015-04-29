class Meetup < Struct.new(:month, :year)
  def sunteenth           ;  find_teenth(0)   ;  end
  def monteenth           ;  find_teenth(1)   ;  end
  def tuesteenth          ;  find_teenth(2)   ;  end
  def wednesteenth        ;  find_teenth(3)   ;  end
  def thursteenth         ;  find_teenth(4)   ;  end
  def friteenth           ;  find_teenth(5)   ;  end
  def saturteenth         ;  find_teenth(6)   ;  end

  def first_sunday        ;  first_finder(0)  ;  end
  def first_monday        ;  first_finder(1)  ;  end
  def first_tuesday       ;  first_finder(2)  ;  end
  def first_wednesday     ;  first_finder(3)  ;  end
  def first_thursday      ;  first_finder(4)  ;  end
  def first_friday        ;  first_finder(5)  ;  end
  def first_saturday      ;  first_finder(6)  ;  end

  def second_sunday       ;  second_finder(0) ;  end
  def second_monday       ;  second_finder(1) ;  end
  def second_tuesday      ;  second_finder(2) ;  end
  def second_wednesday    ;  second_finder(3) ;  end
  def second_thursday     ;  second_finder(4) ;  end
  def second_friday       ;  second_finder(5) ;  end
  def second_saturday     ;  second_finder(6) ;  end

  def third_sunday        ;  third_finder(0)  ;  end
  def third_monday        ;  third_finder(1)  ;  end
  def third_tuesday       ;  third_finder(2)  ;  end
  def third_wednesday     ;  third_finder(3)  ;  end
  def third_thursday      ;  third_finder(4)  ;  end
  def third_friday        ;  third_finder(5)  ;  end
  def third_saturday      ;  third_finder(6)  ;  end

  def fourth_sunday       ;  fourth_finder(0) ;  end
  def fourth_monday       ;  fourth_finder(1) ;  end
  def fourth_tuesday      ;  fourth_finder(2) ;  end
  def fourth_wednesday    ;  fourth_finder(3) ;  end
  def fourth_thursday     ;  fourth_finder(4) ;  end
  def fourth_friday       ;  fourth_finder(5) ;  end
  def fourth_saturday     ;  fourth_finder(6) ;  end

  def last_sunday         ;  last_finder(0)   ;  end
  def last_monday         ;  last_finder(1)   ;  end
  def last_tuesday        ;  last_finder(2)   ;  end
  def last_wednesday      ;  last_finder(3)   ;  end
  def last_thursday       ;  last_finder(4)   ;  end
  def last_friday         ;  last_finder(5)   ;  end
  def last_saturday       ;  last_finder(6)   ;  end

  def find_teenth(wday)   ;  finder(13, wday) ;  end
  def first_finder(wday)  ;  finder(1, wday)  ;  end
  def second_finder(wday) ;  finder(8, wday)  ;  end
  def third_finder(wday)  ;  finder(15, wday) ;  end
  def fourth_finder(wday) ;  finder(22, wday) ;  end

  def finder(first_day, wday)
    current_date = Date.new(year, month, first_day)
    while current_date.wday != wday
      current_date += 1
    end
    current_date
  end

  def last_finder(wday)
    current_date = Date.new(year, month, -1)
    while current_date.wday != wday
      current_date -= 1
    end
    current_date
  end

  def wday_for(weekday)
    case weekday
    when :sunday    then 0
    when :monday    then 1
    when :tuesday   then 2
    when :wednesday then 3
    when :thursday  then 4
    when :friday    then 5
    when :saturday  then 6
    end
  end
end
