class SecretHandshake
  SECRET = {
    1 => "wink",
    2 => "double blink",
    4 => "close your eyes",
    8 => "jump"
  }

  def initialize(secret)
    if secret.is_a? Integer
      @secret = secret
    else
      @secret = 0
    end
  end

  def commands
    words = []

    if @secret >= 16
      @secret -= 16
      bin = process_binary(true)
    else
      bin = process_binary
    end

    bin.each do |val|
      words << SECRET[val]
    end

    words
  end

  private

  def process_binary(rev = false)
    result = []

    SECRET.keys.reverse.each do |val|
      if @secret >= val
        @secret -= val
        result << val
        # [8, 4, 2, 1]
      end
    end

    rev ? result : result.reverse
  end
  
end

# 31 => 11111
