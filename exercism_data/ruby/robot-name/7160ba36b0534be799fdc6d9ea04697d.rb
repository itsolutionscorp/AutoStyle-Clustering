class Robot
  attr_accessor :name

  CHAR_RANGE = 65..90

  def initialize
    reset
  end

  def reset
    @name = ''

    @name << random_prefix << random_suffix
  end

  def random_prefix
    Random.rand(CHAR_RANGE).chr << Random.rand(CHAR_RANGE).chr
  end

  def random_suffix
    Random.rand(100..999).to_s
  end

  private :random_prefix, :random_suffix
end
