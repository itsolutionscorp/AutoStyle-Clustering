class Robot
  attr_accessor :name

  @@robots = []

  def initialize
    @name = generate_name()
  end

  def reset
    old_name = @name
    @name = generate_name()
    @@robots.delete(old_name)
  end

  private

  def generate_name
    10.times do
      new_name = (0..1).map { (65 + rand(26)).chr }.join + "%03d" % rand(1000)
      next if @@robots.include?(new_name)

      @@robots.push(new_name)
      return new_name
    end
    raise "Did not found a unique new name."
  end


end
