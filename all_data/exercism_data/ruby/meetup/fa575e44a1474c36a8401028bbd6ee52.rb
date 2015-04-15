class Meetup
  require 'date'
  define_method("test_method") {|test| "Hello World! #{test}"}

  def initialize(month, year)
  	@month = month
  	@year = year

	  @days = %W(monday tuesday wednesday thursday friday saturday sunday)

	  @teenth_methods = %W(mon tues wednes thurs fri satur sun).map {|day| day+"teenth"}
	  @teenth_hash = Hash[ *@teenth_methods.map.each_with_index { |element, index| [element, @days[index]] }.flatten ]

	  @weeks_to_days = {
	  	'first' 	=> [1, 7], 
	  	'second' 	=> [8, 14], 
	  	'third' 	=> [15, 21], 
	  	'fourth' 	=> [22, 28], 
	  	'last' 		=> [Date.civil(year, month, -7).day, Date.civil(year, month, -1).day]
  	}
  	
  	generate_teenth_methods
  	generate_week_methods
  end

  private

  	def generate_teenth_methods
	  	@weeks_to_days.keys.each do |week|
	  		@days.each do |day|
	  			generate_method("#{week}_#{day}", @weeks_to_days[week][0], @weeks_to_days[week][1], day)
	  		end
	  	end
  	end

  	def generate_week_methods
	  	@teenth_methods.each do |method|
	  		generate_method(method, 13, 19, @teenth_hash[method])
	  	end
  	end


		def generate_method(method_name, start_day, end_day, weekday)
			self.class.send(:define_method, method_name) do
				start_date = 	Date.new(@year, @month, start_day)
				end_date = 		Date.new(@year, @month, end_day)
				(start_date..end_date).select { |date| date.send("#{weekday}?".to_sym) }[0]
			end
	end
end
