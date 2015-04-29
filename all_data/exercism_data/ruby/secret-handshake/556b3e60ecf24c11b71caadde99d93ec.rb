class SecretHandshake
  def initialize(num)
    @num = num
  end

  attr_reader :num

  def commands
    ans = []
    if num == num.to_i
      binary = num.to_s(2)
    else
      return ans
    end

    if binary[-1] == '1'
      ans << 'wink'
    end

    if binary[-2] == '1'
      ans << 'double blink'
    end

    if binary[-3] == '1'
      ans << 'close your eyes'
    end

    if binary[-4] == '1'
      ans << 'jump'
    end

    if binary[-5] == '1'
      ans = ans.reverse
    end
    ans
  end
end
