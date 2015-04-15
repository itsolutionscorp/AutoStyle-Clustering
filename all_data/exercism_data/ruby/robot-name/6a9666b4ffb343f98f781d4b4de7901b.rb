require 'singleton'

class Robot
  NAME_RANGE = ('AA000'..'ZZ999')

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @@names ||= NAME_RANGE.to_enum
    @name = @@names.next
  end
end
