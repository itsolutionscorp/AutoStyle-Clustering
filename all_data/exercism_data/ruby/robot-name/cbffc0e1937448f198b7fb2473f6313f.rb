class Robot
  chars         = [*'A'..'Z'].freeze
  digits        = [*'1'..'9'].freeze
  NAME_TEMPLATE = [chars]*2 + [digits]*3

  def self.generate_name
    NAME_TEMPLATE.map(&:sample).join('')
  end

  attr_reader :name

  def initialize(name = self.class.generate_name)
    @name = name
  end

  def reset
    initialize
  end
end
