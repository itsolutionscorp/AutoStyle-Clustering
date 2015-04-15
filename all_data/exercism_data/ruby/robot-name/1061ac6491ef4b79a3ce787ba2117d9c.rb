class Robot

# class methods & initializations
  class << self
    attr_accessor :roster
  end
  self.roster = []
  ALPHA_CNT = 2
  NUM_CNT   = 3

  # instance methods & initialization 
  def initialize
    @name = nil
  end

  def name
    @name ||= unique_random_name
  end

  def reset
    remove_from_roster
    @name = nil
  end

private
  def unique_random_name
    rn = random_name
    while Robot.roster.include? rn
      rn = random_name
    end
    Robot.roster << rn
    rn
  end

  def remove_from_roster
  end

  def random_name
    rn = ''
    ALPHA_CNT.times {rn << random_UC_char }
    NUM_CNT.times {rn << random_digit }
    rn
  end

  def random_digit
    [*('0'..'9')].sample(1).first
  end

  def random_UC_char
    [*('A'..'Z')].sample(1).first
  end
end
