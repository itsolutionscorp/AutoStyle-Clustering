class Year
  def initialize(value)
    @value = value
  end

  def leap?
    @value % 4 == 0 &&
      @value % 100 != 0 ||
        @value % 400 == 0
  end
end
