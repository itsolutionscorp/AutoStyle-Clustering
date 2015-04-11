module Digits
  def thousands_value
    self / 1000
  end

  def hundreds_value
    (self % 1000) / 100
  end

  def tens_value
    (self % 100) / 10
  end

  def ones_value
    (self % 10)
  end
end
