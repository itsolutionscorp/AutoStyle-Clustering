require_relative 'year'

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
