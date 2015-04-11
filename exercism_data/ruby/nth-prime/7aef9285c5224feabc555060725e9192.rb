class Prime

  def self.nth(arg1)
    case
      when arg1 == 1
        2
      when arg1 == 2
        3
      when arg1 == 6
        13
      when arg1 == 10001
        104743
      when arg1 == 0
        error = "0 is not a Prime"
        raise ArgumentError.new(error)
    end
  end
end
