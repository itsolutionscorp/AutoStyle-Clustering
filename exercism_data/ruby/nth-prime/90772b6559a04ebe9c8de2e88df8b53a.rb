class Prime

  def self.nth n
    raise ArgumentError if n < 1
    counter = 1
    value   = 2
    loop do
      return value if counter == n
      value += 1
      (2...value).each do |n|
        break if value % n == 0
        counter += 1 if n == value - 1 && value % n != 0
      end
    end
  end

end
