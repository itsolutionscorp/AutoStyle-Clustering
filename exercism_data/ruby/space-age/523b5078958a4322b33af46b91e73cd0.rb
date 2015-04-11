class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end


  divisors = {
    :mercury => 7600525.80,
    :venus => 19411026.18,
    :earth => 31557600.00,
    :mars => 59359776.79,
    :jupiter => 374222565.15,
    :saturn => 929292362.88,
    :uranus => 2652994591.74,
    :neptune => 5200418560.03,
  }

  divisors.each do |planet, divisor|
    define_method("on_#{planet}") do
      (seconds / divisor).round(2)
    end
  end

end
