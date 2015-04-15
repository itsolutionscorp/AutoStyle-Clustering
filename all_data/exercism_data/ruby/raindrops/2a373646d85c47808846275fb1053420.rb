class Raindrops

  class << self
    def convert(number)
      find_responses_by_factors(number)
      if @response.any?
        @response.join('')
      else
        number.to_s
      end
    end

    def find_responses_by_factors(number)
      @response = []
      @response << "Pling" if number % 3 == 0
      @response << "Plang" if number % 5 == 0
      @response << "Plong" if number % 7 == 0
    end
  end

end
