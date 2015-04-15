class Robot
  attr_reader :name
  @@robots = []

  def self.count
    @@robots.count
  end

  def self.search(name)
    @@robots.include?(name) ? name : nil
  end

  def self.list
    @@robots.sort.each {|name| puts name}
  end

  def initialize
    new_name
  end

  def reset
    @@robots.delete_if {|name| name == @name}
    new_name
  end

private
  def generate_name
    alphabet = ('A'..'Z').to_a

    2.times.map { alphabet.sample }.join + rand(100..999).to_s
  end

  def new_name
    @name = generate_name
    if Robot.search(@name)
      new_name
    end
    @@robots << @name
  end
end
