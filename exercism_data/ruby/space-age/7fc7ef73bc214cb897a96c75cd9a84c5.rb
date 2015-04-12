class SpaceAge

  attr_reader :age_in_seconds

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

  planet_seconds_per_year.each do |planet, seconds|
    send :define_method, "on_#{planet}" , lambda {age(seconds)}
  end

  def initialize(seconds)
    @age_in_seconds = seconds
  end

  def seconds
    age_in_seconds
  end

  def age(planet_year_seconds)
    (age_in_seconds.to_f / planet_year_seconds.to_f).round(2)
  end

end