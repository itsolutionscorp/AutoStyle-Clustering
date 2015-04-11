require 'set'

class Robot

  SERIAL_NUM_FMT = 'AA###'
  @@name_history = Set.new

  def create_name
    def generate_name
      temp_name = ''

      SERIAL_NUM_FMT.each_char {|d|
        if d == 'A'
          temp_name << rand(65..65 + 25).chr
        elsif d == '#'
          temp_name << rand(0..9).to_s
        end
        }
      temp_name
    end

    @name = generate_name

    while @@name_history.include? @name
      @name = generate_name
    end
    @@name_history.add(@name)
    @name
  end

  def name
    @name ||= create_name
  end

  def reset
    @name = nil
  end

end
