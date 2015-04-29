class Prime

  require 'mathn'

  def self.nth(num)
    raise ArgumentError if num == 0
    prime_number = Prime.first(num).last
    prime_number
  end

end
