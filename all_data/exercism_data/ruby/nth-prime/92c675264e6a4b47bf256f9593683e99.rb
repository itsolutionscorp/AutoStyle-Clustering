require 'prime'
class Prime

  def self.nth(num)
    num == 0 ? (raise ArgumentError) : Prime.first(num).last
  end

end
