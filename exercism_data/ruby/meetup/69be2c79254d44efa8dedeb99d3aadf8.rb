require 'date'

class Meetup

	def initialize(month, year)
		@year = year
		@month = month
	end

	ordinals = {'first' => 1, 'second' => 8, 'third' => 15, 'fourth' => 22}
	weekdays = %W[sun mon tues wednes thurs fri satur]

	(0..6).each do |weekday|
		ordinals.each do |ordinal, ordinal_v|
			define_method "#{ordinal}_#{weekdays[weekday]}day" do
				return Date.new(@year, @month, ordinal_v).step(Date.new(@year,-1,-1)).find{|d| d.wday == weekday}
			end
		end
		define_method "last_#{weekdays[weekday]}day" do
			return Date.new(@year, @month, -1).downto(0).find{|d| d.wday == weekday}
		end
		define_method "#{weekdays[weekday]}teenth" do
			return Date.new(@year, @month, 13).step(Date.new(@year,-1,-1)).find{|d| d.wday == weekday}
		end
	end
end
