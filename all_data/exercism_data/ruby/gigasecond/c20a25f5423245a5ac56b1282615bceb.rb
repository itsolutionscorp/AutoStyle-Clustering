class Gigasecond
	def initialize(date)
		@date = date
	end

    def date
        @date + 11574
    end

    #     newdate(@date)
    # end

    # def newdate(date)
    #     time = date.to_time
    #     time = time + (10**9)
    #     date = time.to_date
    # end
end
