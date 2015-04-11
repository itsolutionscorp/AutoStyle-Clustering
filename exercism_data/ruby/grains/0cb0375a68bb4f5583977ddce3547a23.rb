class Grains
  def initialize
    @intent = Hash.new(proc {})
    @intent[:square] = ->(rice) { 2**(rice - 1) }
    @intent[:total] = -> { (2**64) - 1 }
    @mechanisms = @intent.keys
  end

  def method_missing(mechanism, *args)
    @intent[mechanism][*args.first] || super
  end

  def respond_to_missing?(intent, *)
    @mechanisms.map(&:to_s).include?(intent.to_s)
  end
end
