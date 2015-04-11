# Robot Class
class Robot
  attr_accessor :name

  NAME_LENGTH = 5
  ALPHA_PORTION_LENGTH = 2

  class << self
    attr_accessor :names

    private

    def build_name
      NAME_LENGTH.times.to_a.each_with_object('') do |i, name|
        range = (i < ALPHA_PORTION_LENGTH) ? ('A'..'Z') : ('0'..'9')
        name << random_char_in_range(range)
      end
    end

    def random_char_in_range(range)
      range.to_a.sample
    end
  end

  def self.generate_name
    self.names ||= []
    new_name = nil

    loop do
      new_name = build_name
      break unless self.names.include? new_name
    end

    self.names.push(new_name).last
  end

  def initialize
    reset
  end

  def reset
    @name = self.class.generate_name
  end
end
