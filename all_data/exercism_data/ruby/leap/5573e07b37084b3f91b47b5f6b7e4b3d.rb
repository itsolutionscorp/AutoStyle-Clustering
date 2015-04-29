class Year

  def initialize(subject)
    @subject = subject
  end

  def leap?
    divisible_by_400? || (divisible_by_4? && !divisible_by_100?)
  end

  private

  attr_reader :subject

  def divisible_by_4?
    subject % 4 == 0
  end

  def divisible_by_100?
    subject % 100 == 0
  end

  def divisible_by_400?
    subject % 400 == 0
  end

end
