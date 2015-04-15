class SecretHandshake
  def initialize(decimal)
    @decimal = decimal.to_i
    @binary = to_binary
  end


  def commands
    command_array = []
    command_array << "wink" if binary_array[0] == "1"
    command_array << "double blink" if binary_array[1] == "1"
    command_array << "close your eyes" if binary_array[2] == "1"
    command_array << "jump" if binary_array[3] == "1"
    command_array.reverse! if binary_array[4] == "1"
    command_array
  end


  def to_binary
    binary_array.map do |num|
      if num.nil?
        "0"
      else
        num
      end
    end
  end

  def binary_array
    top = @decimal
    bin = []
    while top > 0 do
      top.downto(0) do |i|
        if top >= (2 ** i)
          bin[i] = "1" 
          return bin if i == 0
          top -= (2 ** i)
          break if top <= 0
        end
      end
    end
    bin
  end
end
