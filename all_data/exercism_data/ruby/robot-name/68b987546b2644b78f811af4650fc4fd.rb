class Robot
  attr_accessor :name

  @@USED_NAMES = []

  def initialize
    generate_name
  end

  def generate_name
    name_factory  = { 
      letters: (('a'..'z').to_a) + (('A'..'Z').to_a),
      numbers: (1..9).to_a}

    combination = [[:letters, 2], [:numbers, 3]]

    @name = ''
    combination.each do |type, count|
      1.upto(count) do
        @name += name_factory[type].sample.to_s
      end
    end

    generate_name if @@USED_NAMES.include?(@name)
  end

  def reset
    generate_name
  end
end
