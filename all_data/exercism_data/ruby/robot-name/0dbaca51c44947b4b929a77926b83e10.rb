class Robot

  attr_accessor :name

  POSSIBLE_LETTERS = ('a'..'z').to_a.concat(('A'..'Z').to_a)
  ALPHA_OPTIONS = POSSIBLE_LETTERS.length
  DECIMAL_OPTIONS = (0..10).to_a.length

  def initialize
    reset
  end

  def reset
    self.name = Robot.create_new_name
  end

  private

  def self.new_name
    name = []
    2.times { name << POSSIBLE_LETTERS.sample}
    name << "%03d" % rand(1000)
    return name.join
  end

  def self.create_new_name
    @@names ||= []
    name_candidate = new_name
    raise "NoNamesLeft" if @@names.length >= ALPHA_OPTIONS**2 * DECIMAL_OPTIONS**2
    while @@names.include? name_candidate
      name_candidate = new_name
    end
    @@names << name_candidate
    name_candidate
  end
end
