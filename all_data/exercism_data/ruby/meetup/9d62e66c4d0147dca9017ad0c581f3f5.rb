require 'date'

class Meetup
  DAYS_OF_WEEK = %w{mon tues wednes thurs fri satur sun}
  ORDER = {first: 0, second: 1, third: 2, fourth: 3, last: -1}
  TEENS = 13..19

  def initialize(month, year)
    @date = Date.new year, month
  end

  DAYS_OF_WEEK.each do |wday|
    ORDER.each do |nth, index|
      define_method "#{nth}_#{wday}day".to_sym do        
        days_this_month.select(&"#{wday}day?".to_sym)[index] 
      end                                       
    end
    define_method "#{wday}teenth".to_sym do
      days_this_month.select(&"#{wday}day?".to_sym).find {|date| TEENS.cover? date.day}
    end                                    
  end

  private
  def days_this_month
    @days_this_month ||= @date...@date.next_month
  end
end
# methods created by this class: first_monday, second_monday, third_monday, fourth_monday, last_monday, monteenth, first_tuesday, second_tuesday, third_tuesday, fourth_tuesday, last_tuesday, tuesteenth, first_wednesday, second_wednesday, third_wednesday, fourth_wednesday, last_wednesday, wednesteenth, first_thursday, second_thursday, third_thursday, fourth_thursday, last_thursday, thursteenth, first_friday, second_friday, third_friday, fourth_friday, last_friday, friteenth, first_saturday, second_saturday, third_saturday, fourth_saturday, last_saturday, saturteenth, first_sunday, second_sunday, third_sunday, fourth_sunday, last_sunday, sunteenth]
