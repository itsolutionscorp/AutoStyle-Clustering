class Robot
  attr_accessor :name
  @@r = Random.new

  def initialize
    create_random_name
  end

  def reset
    create_random_name
  end

  private
  def create_random_name
    s = ''
    2.times { s << @@r.rand('A'.ord .. 'Z'.ord).chr }
    s << sprintf("%03d", @@r.rand(0..999))
    @name = s
  end
end
