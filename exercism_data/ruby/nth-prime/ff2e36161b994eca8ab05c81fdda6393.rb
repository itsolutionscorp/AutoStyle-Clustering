require 'prime'
# class to work out nth prime
class Prime
  def self.nth(n)
    raise ArgumentError if n.zero?
    Prime.instance.each.inject(0) { |res, p| return p if res == n-1; res+=1 }
  end
end
