require 'date'

class Meetup
  DAYS_OF_WEEK = %w{mon tues wednes thurs fri satur sun}
  ORDER = {first: 0, second: 1, third: 2, fourth: 3, last: -1}
  TEENS = 13..19

  def initialize(month, year)
    @year = year
    @month = month
  end

  DAYS_OF_WEEK.each do |day|
    ORDER.each do |nth, index|
      class_eval <<-RUBY
        def #{nth}_#{day}day        
          all_days_in_month.select(&:#{day}day?)[#{index}] 
        end                                     
      RUBY
    end
    class_eval <<-RUBY
      def #{day}teenth
        all_days_in_month.select(&:#{day}day?).find {|date| TEENS.cover? date.day}
      end                                    
    RUBY
  end

  private
  def all_days_in_month
    @month_days ||= Date.new(@year, @month)...Date.new(@year + optional_rollover, next_month)
  end

  def optional_rollover
    @month == 12 ? 1 : 0
  end

  def next_month
    @month == 12 ? 1 : @month + 1
  end
end
