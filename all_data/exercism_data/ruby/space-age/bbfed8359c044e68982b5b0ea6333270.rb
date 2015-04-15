class SpaceAge

  attr_reader :seconds

  planet_seconds_per_year = {
                              mercury: 7600526,
                              venus: 19411026,
                              earth: 31557600,
                              mars: 59359777,
                              jupiter: 374222565,
                              saturn: 928792570,
                              uranus: 2652994592,
                              neptune: 5196280668
                            }

  planet_seconds_per_year.each do |planet, year_seconds|
    send :define_method, "on_#{planet}" , lambda {
      (seconds.to_f / year_seconds.to_f).round(2)
    }
  end

  def initialize(seconds)
    @seconds = seconds
  end

end
