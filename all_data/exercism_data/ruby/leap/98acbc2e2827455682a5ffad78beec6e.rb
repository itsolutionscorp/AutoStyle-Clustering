class Year
  attr_reader :annum
  def initialize(annum)
    @annum = annum
  end

  def leap?
    divisible_by_four? && !century? || exceptional_century?
  end

  private

  def century?
    annum % 100 == 0
  end

  def divisible_by_four?
    annum % 4 == 0
  end

  def exceptional_century?
    annum % 400 == 0
  end
end
