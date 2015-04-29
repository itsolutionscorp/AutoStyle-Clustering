class Robot
  attr_accessor :name


  def initialize
    factory_settings
  end

  def reset
    factory_settings
  end

  private

  def factory_settings
    @name = generate_name
  end

  def generate_name
    prefix = name_component(2, 'A'..'Z')
    suffix = name_component(3, '0'..'9')
    "#{prefix}#{suffix}"
  end

  def name_component(size, range)
    Array.new(size){[*range].sample}.join
  end
end
