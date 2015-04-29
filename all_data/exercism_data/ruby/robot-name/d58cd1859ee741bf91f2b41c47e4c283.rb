RobotNameGenerator = Enumerator.new do |yielder|
  name = "AA000"
  while name != "AAA000"
    yielder << name
    name = name.next
  end
end

RobotRandomNameGenerator = Enumerator.new do |yielder|
  names = ('AA000'...'AAA000').to_a.shuffle
  yielder << names.pop until names.empty?
end

class Robot
  attr_accessor :name_generator
  attr_reader   :name

  def initialize(options = {})
    @name_generator = options.fetch(:name_generator) { RobotRandomNameGenerator }

    reset
  end

  def reset
    @name = name_generator.next
  end
end
