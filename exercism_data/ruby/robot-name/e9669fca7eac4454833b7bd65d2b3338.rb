class Robot

  @@used_names = []
  FIRST_CHARSET = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
  SECOND_CHARSET = '1234567890'

  def initialize
    @name = generate_name
  end

  def name
    @name
  end

  def reset
    @name = generate_name
  end

  def generate_name
    name = ''
    while true
      2.times {name << FIRST_CHARSET[rand(FIRST_CHARSET.size)]}
      3.times {name << SECOND_CHARSET[rand(SECOND_CHARSET.size)]}
      if !@@used_names.include? name
        break
      end
    end
    @@used_names << name
    @name = name
  end
end
