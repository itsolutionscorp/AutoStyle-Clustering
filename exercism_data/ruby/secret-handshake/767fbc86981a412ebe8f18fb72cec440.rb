class SecretHandshake
  def initialize(num)
    @num = num
    @keys = {
      1 => "wink",
      10 => "double blink",
      100 => "close your eyes",
      1000 => "jump"
    }
  end

  def commands
    if @num.is_a?(Integer)
      val = ("%b" % @num).to_i
      @keys[val] ? [@keys[val]] : build_results(val)
    else
      []
    end
  end

  private

  def build_results(val)
    count = val.to_s.split(//).count - 1

    temp_val = val
    results = []
    while temp_val != 0
      base = (temp_val / (10**count)) * 10**count
      remainder = temp_val % 10**count

      results << @keys[base] if @keys[base]

      temp_val = remainder #reset temp to current remainder
      count -= 1 #decrement
    end

    val > 10000 ? results : results.reverse
  end
end
