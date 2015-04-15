class Robot

  USED_NAMES = []

  def self.used_names
    USED_NAMES
  end

  attr_accessor :name

  def initialize
    @name = name_generator
    self.class.used_names << self.name
  end

  def reset
    @name = name_generator
  end

  private
    def name_generator
      name = ('A'..'Z').to_a.sample(2).join + rand.to_s[0..3].gsub('.', '')
      self.class.used_names.include?(name) ? name_generator : name
    end
end
