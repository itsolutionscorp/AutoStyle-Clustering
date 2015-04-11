class Meetup
  attr_reader :start_date, :end_date, :days_in_month

  DAYS_OF_THE_WEEK    = %w[monday tuesday wednesday thursday friday saturday sunday]
  OCCURENCES_IN_WORDS = %w[first second third fourth]

  def initialize(month, year)
    @start_date    = Date.new(year, month, 1)
    @end_date      = Date.new(year, month, -1)
    @days_in_month = (start_date..end_date).to_a
  end

  # defines methods mondays, tuesdays, .. sundays
  DAYS_OF_THE_WEEK.each do |day|
    define_method "#{day}s" do
      days_in_month.select{|d| d.send("#{day}?") }
    end
  end

  # defines methods for first_monday, second_monday, .. 
  OCCURENCES_IN_WORDS.each_with_index do |occurence, index|
    DAYS_OF_THE_WEEK.each do |day|
      define_method "#{occurence}_#{day}" do
        send("#{day}s")[index]
      end
    end
  end

  # defines methods for monteenth..sunteenth
  DAYS_OF_THE_WEEK.each do |day|
    define_method "#{day.gsub("day","")}teenth" do
      send("#{day}s").find{|date| date.day > 12 && date.day < 20}
    end
  end

  # defines methods last_monday..last_sunday
  DAYS_OF_THE_WEEK.each do |day|
    define_method "last_#{day}" do
      send("#{day}s").last
    end
  end
end
