class Robot
  attr_accessor :name

  def self.taken_names
    @taken_names || []
  end

  def name
    @name ||= find_unique_name
  end

  def reset
    @name = nil
  end

private

  def find_unique_name
    begin
      name = NameGenerator.get
    end while Robot.taken_names.include? name

    name
  end

end

class NameGenerator
  class << self

    def get char_ammount = 2, digit_ammount = 3
      random_string( char_ammount ) + random_number( digit_ammount )
    end

  private

    def random_string length
      length.times.map { (65 + rand(26)).chr }.join
    end

    def random_number length
      length.times.map { (48 + rand(10)).chr }.join
    end

  end
end
