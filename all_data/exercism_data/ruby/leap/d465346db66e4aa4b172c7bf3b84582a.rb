class Year
   attr_accessor :input

  def initialize(input)
    @input = input
  end

  def leap?
    divisible_by_four_hundred? || divible_by_four?
  end

  private

  def divisible_by_four_hundred?
    @input % 400 == 0
  end


  def divible_by_four?
    @input % 4 == 0
  end
end
