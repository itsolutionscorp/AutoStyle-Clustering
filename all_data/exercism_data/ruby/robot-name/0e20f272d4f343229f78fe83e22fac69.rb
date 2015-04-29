class Robot
  GENERATOR = ('AA000'..'ZZ999').to_enum

  attr_reader :name

  def reset
    @name = GENERATOR.next
  end

  alias :initialize :reset
end
