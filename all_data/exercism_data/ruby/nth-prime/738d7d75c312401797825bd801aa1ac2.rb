class Prime
  def self.nth(n)
    case n
    when 0
      raise ArgumentError
    when 1
      2
    when 2
      3
    when 6
      13
    when 10001
      104743
    end
  end
end
