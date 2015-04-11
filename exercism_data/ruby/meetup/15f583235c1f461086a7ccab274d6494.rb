require 'date'

class Meetup

  attr_reader :year
  attr_reader :month

    weekdays = [:mon, :tues, :wednes, :thurs, :fri, :satur, :sun]
    ordinals = {:first => 1, :second => 8, :third => 15, :fourth => 22}

    def initialize(mth, yr)
        @year = yr
        @month = mth
    end


    weekdays.each do |day|
      ordinals.each do |ordinal, ordinal_v|

        define_method ("#{ordinal}_#{day}day") do
          return Date.new(year, month, ordinal_v).step(Date.new(year, -1, -1)).find{
            |d| d.send("#{day}day?")
          }
        end
      end

      define_method ("last_#{day}day") do
        return Date.new(year, month, -1).downto(0).find{ |d| d.send("#{day}day?")}
      end

      define_method( "#{day}teenth") do
        return Date.new(year, month, 13).step(Date.new(year, -1, -1)).find{
          |d| d.send("#{day}day?")
        }
      end
    end
end
