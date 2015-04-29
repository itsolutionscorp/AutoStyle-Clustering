class Robot
  attr_accessor :name

  # bad due to possible later inheritance
  # not sure how to do better
  @@robots = []

  def initialize
    @name = generate_model_number
  end

  def reset
    @name = generate_model_number
  end

  private
  # Generates a random name from a set of easily readable characters
  # based on http://stackoverflow.com/a/493230
  def generate_model_number
    charset = %w{ A C D E F G H J K M N P Q R T V W X Y Z }
    numset = %w{ 2 3 4 6 7 9 }
    name = ""
    name += (0...2).map{ charset.to_a[rand(charset.size)] }.join
    name += (0...3).map{ numset.to_a[rand(numset.size)] }.join
    name = generate_model_number if @@robots.include?(name)
    @@robots << name
    name
  end
end
