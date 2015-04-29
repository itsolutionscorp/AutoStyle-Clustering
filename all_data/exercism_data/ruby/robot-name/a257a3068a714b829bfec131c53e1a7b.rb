# Robot factory settings
class Robot
  @all_names = {}

  class << self
    attr_accessor :all_names
  end

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = unique_name
  end

  private

  def generate_name
    alphabet = [('a'..'z'), ('A'..'Z')].map(&:to_a).flatten
    alpha = (0...2).map { alphabet[rand(alphabet.length)] }.join
    num = rand(1000).to_s.rjust(3, '0')
    alpha + num
  end

  def unique_name
    name = generate_name
    name = unique_name if Robot.all_names.key?(name)
    Robot.all_names[name] = true
    name
  end
end
