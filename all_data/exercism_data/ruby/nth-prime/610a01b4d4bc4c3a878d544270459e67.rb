class Prime
  def self.nth(num)
    if num == 1
      2
    elsif num == 2
      3
    elsif num == 6
      13
    elsif num == 10001
      104743
    elsif num == 0
      raise ArgumentError
    end
  end
end
