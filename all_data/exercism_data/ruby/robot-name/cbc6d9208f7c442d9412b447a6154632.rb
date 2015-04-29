class Robot
  @@next_unit = 0
  def initialize
    assign_unit_number
  end

  def name
    "BU#{formatted_unit_number}"
  end

  def reset
    assign_unit_number
  end

  private

  def assign_unit_number
    @unit_number = @@next_unit
    @@next_unit += 1
  end

  def formatted_unit_number
    @unit_number.to_s.rjust(3, '0')
  end
end
