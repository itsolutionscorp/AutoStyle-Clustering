class Year
   attr_accessor :input

  def initialize(input)
    @input = input
  end

  def leap?
    (divisible_by_four_hundred? or not divible_by_hundred?) and divible_by_four?
  end

  private

  def divisible_by_four_hundred?
    @input % 400 == 0
  end


  def divible_by_four?
    @input % 4 == 0
  end

  def divible_by_hundred?
    @input % 100 == 0
  end
end
