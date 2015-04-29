require 'set'

class Robot
  @@name_generator = ('AA000'..'ZZ999').to_enum
  def name
    @name ||= @@name_generator.next
  end

  def reset
    @name = nil
  end
end
