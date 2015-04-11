class Robot
  CHARS = ('a'..'z').to_a
  DIGITS = ('0'..'9').to_a

  def name
    @name ||= [[CHARS.sample]*2, [DIGITS.sample]*3].join
  end

  def reset
    @name = nil
  end
end
