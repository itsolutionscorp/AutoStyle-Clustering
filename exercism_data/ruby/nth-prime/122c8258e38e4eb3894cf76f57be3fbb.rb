require 'prime'
# class to work out nth prime
class Prime
  def self.nth(n)
    raise ArgumentError if n.zero?
    Prime.instance.first(n).last
  end
end
