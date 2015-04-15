class Robot
  def name
    return @name if @name

    result = ""

    2.times do
      result << (65 + rand(26)).chr
    end

    result << rand(100...1000).to_s

    @name = result
  end

  def reset
    @name = nil
  end
end
