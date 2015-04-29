class Year
  def self.leap?(yr)
    Year.new(yr).leap?
  end

  def initialize(yr)
    @yr = yr
  end

  def leap?
    regular? && (!hundred? || four_hundred?)
  end

  private

  def regular?
    @yr % 4 == 0
  end

  def hundred?
    @yr % 100 == 0
  end

  def four_hundred?
    @yr % 400 == 0
  end
end
