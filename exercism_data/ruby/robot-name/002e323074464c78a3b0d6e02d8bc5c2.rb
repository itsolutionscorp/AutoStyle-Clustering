class Robot
  attr_reader :name

  class << self
    def find_name
      @robots ||= robots_names
      raise 'Exhaused robot names' if @robots.count == 0
      @robots.shift
    end

    def robots_names
      given_names = [*'AA'..'ZZ']
      surnames = [*'000'..'999']
      given_names.product(surnames).shuffle.map &:join
    end
  end
  
  def reset
    @name = self.class.find_name
  end
  alias_method :initialize, :reset 

end
