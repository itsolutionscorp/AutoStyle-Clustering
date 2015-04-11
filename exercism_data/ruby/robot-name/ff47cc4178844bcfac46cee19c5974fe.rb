class Robot
  class << self
    def invalid?(name)
      invalid_names.include?(name)
    end

    def retire(name)
      invalid_names << name
    end

  private

    def invalid_names
      @invalid_names ||= [nil]
    end
  end

  def initialize
    @name = candidate_name while Robot.invalid?(@name)
    Robot.retire(@name)
  end

  def name
    @name
  end

  def reset
    initialize
  end

private

  def candidate_name
    "#{letter}#{letter}#{number}#{number}#{number}"
  end

  def letter
    ('A'.ord + rand(26)).chr
  end

  def number
    rand(10).to_s
  end
end
