class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

    def generate_name
      prefix = ('AA'..'ZZ').to_a.sample
      suffix = (100..999).to_a.sample.to_s
      prefix + suffix
    end
end
