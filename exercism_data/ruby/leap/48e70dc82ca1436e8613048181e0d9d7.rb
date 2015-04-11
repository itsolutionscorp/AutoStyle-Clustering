class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if @year.modulo(4).zero?
      return true unless @year.modulo(100).zero? and not @year.modulo(400).zero?
    end

    false
  end
end
