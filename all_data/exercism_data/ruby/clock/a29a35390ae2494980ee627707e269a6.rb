MINUTES_PER_DAY = 24 * 60

class Clock
	private_class_method :new

	def initialize(hours=0, minutes=0)
		@total_minutes = hours * 60 + minutes

		@total_minutes = total_minutes % MINUTES_PER_DAY if total_minutes > MINUTES_PER_DAY
		@total_minutes += MINUTES_PER_DAY if total_minutes < 0
	end

	def self.at(hours=0, minutes=0)
		new(hours, minutes)
	end

	def to_s
		"%02d:%02d" % [total_minutes / 60, total_minutes % 60]
	end

	def +(minutes)
		self.class.at(0, total_minutes + minutes)
	end

	def -(minutes)
		self.class.at(0, total_minutes - minutes)
	end

	def ==(clock2)
		self.class == clock2.class && total_minutes == clock2.total_minutes
	end

	protected

	attr_reader :total_minutes
end
