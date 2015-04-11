class SecretHandshake

  HANDSHAKE = {
    10000 => "reverse",
    1000 => "jump",
    100 => "close your eyes",
    10 => "double blink",
    1 => "wink"
  }

  def initialize(integer)
    integer.to_s.match(/[^0-9]+/) ? @integer = 0 : @integer = integer
  end

  def to_binary
    divided_array = []
    current = @integer
    while current >= 1 
      divided_array << current
      current = current / 2
    end
    divided_array.map do |value|
      value.even? ? 0 : 1
    end.reverse.join.to_i
  end

  def commands
    return [] if @integer == 0
    @binary = to_binary
    results = []

    if @binary >= 10000
      reverse = true
      @binary = @binary - 10000
    end
    if @binary >= 1000
      results << HANDSHAKE[1000]
      @binary = @binary - 1000
    end
    if @binary >= 100
      results << HANDSHAKE[100]
      @binary = @binary - 100
    end
    if @binary >= 10
      results << HANDSHAKE[10]
      @binary = @binary - 10
    end
    if @binary == 1
      results << HANDSHAKE[1]
    end
    if reverse == true
      results
    else
      results.reverse
    end
  end

end
